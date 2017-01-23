package kr.sir.service.board.impl;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.classic.Logger;
import kr.sir.common.exception.ConfigException;
import kr.sir.common.exception.DeleteException;
import kr.sir.common.exception.FindException;
import kr.sir.common.util.CommonUtil;
import kr.sir.domain.Board;
import kr.sir.domain.BoardFile;
import kr.sir.domain.Write;
import kr.sir.domain.form.BoardForm;
import kr.sir.domain.repository.admin.BoardRepository;
import kr.sir.domain.repository.board.BbsEmRepository;
import kr.sir.domain.repository.board.BbsRepository;
import kr.sir.domain.repository.board.FileRepository;
import kr.sir.service.board.BbsService;

@Service
public class BbsServiceImpl implements BbsService {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(BbsServiceImpl.class);

	private int boardId = 0;									// 게시판 번호
	private int pagePerPosts = 10;								// 게시판 페이지 당 게시물 수 (임시로 상수, board 설정 정보 참조)
	
	private BbsRepository bbsRepository;
	private BbsEmRepository bbsEmRepository;
	private FileRepository fileRepository;
	private BoardRepository boardRepository;

	@Autowired
	public void setBoardRepository(BbsRepository bbsRepository) {
		this.bbsRepository = bbsRepository;
	}

	@Autowired
	public void setbbsEmRepository(BbsEmRepository bbsEmRepository) {
		this.bbsEmRepository = bbsEmRepository;
	}
	
	@Autowired
	public void setFileRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}
	
	@Autowired
	public void setBoardRepository(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	// Board Id 셋팅
	public void setBoardId(String boardName) throws FindException {
		int boardId = 0; 
		boardId = boardRepository.findByTable(boardName).getId();
		if(boardId == 0) throw new FindException("게시판 이름으로 게시판 번호를 가져오는데 실패했습니다. 요청된 게시판 이름 : " + boardName);
		if(boardId != this.boardId)	this.boardId = boardId;
	}

	// 게시글 가져오기
	@Override
	public Write findOne(int articleNumber) throws FindException {
		Write article = bbsRepository.findOne(articleNumber);
		if(article == null) throw new FindException("게시글을 가져오는데 실패했습니다. 글번호를 확인해 주세요. 요청된 글번호 : " + articleNumber);
		return bbsRepository.findOne(articleNumber);
	}
	
	// 글쓰기, 수정
	@Override
	public Write insertArticle(Write write, BoardForm boardForm, MultipartFile[] files) throws UnknownHostException, ConfigException, FindException {

		String reply = "";
		int wrNum = 0;									
		if(boardForm.getIsReply() == 1) {					// 답변 글 - 기존 wr_num 그대로
			wrNum = write.getNum();
			reply = createReply(boardForm.getBaseCommentId(), write);		// reply값을 생성
		} else {											// 일반 글 - 기존 wr_num에 -1
			int minNum = bbsEmRepository.findMinNum();
			if(minNum == 0) throw new FindException("게시판에서 가장 작은 wr_num을 가져오는데 실패했습니다.");
			wrNum += minNum -1;	// 게시판에서 가장 작은 wr_num 가져와서 -1
		}
		
		write.setNum(wrNum);
		write.setReply(reply);
		write = setDefaultValueTo(write);					// 입력값 외 기본값 저장
		
		int fileCount = 0;
		for(MultipartFile file : files) {
			if( !file.isEmpty() ) {
				fileCount++;
			}
		}
		write.setFile(fileCount);							// 업로드한 파일 갯수
		write.setMemberId(write.getName());	// 임시			// 세션의 로그인한 정보에서 이름을 찾아서 넣기

		// save하기전에 객체에 넣어져 있는 값이 null 인지 체크하는 로직 추가
		// 이게 노가다 없이 가능한가?
		Write article = bbsRepository.save(write);
		
		// 원글일 때 parent 값을 id값과 동일하게 저장
		article.setParent(article.getId());
		bbsRepository.save(article);
		
		return article;
	}
	
	// 답변글에 들어갈 reply 생성
	public String createReply(int baseArticleId, Write write) throws ConfigException, FindException {
		Write article = bbsRepository.findOne(baseArticleId);
		if(article == null) throw new FindException("답변 글 생성을 위한 원글을 찾아오는데 실패하였습니다. 요청한 원글의 글번호를 확인해 주세요. 요청된 글 번호 : " + baseArticleId);
		String baseReply = CommonUtil.isNull(article.getReply());
		String maxReply = bbsEmRepository.findMaxReplyForAnswer(article.getNum(), baseReply);
		if(maxReply.equals("")) {
			return baseReply + "A";
		} 
		
		char lastChar = maxReply.charAt(maxReply.length() - 1);
		if(lastChar == 'Z') {
			throw new ConfigException("더 이상 답변하실 수 없습니다. 답변은 26개 까지만 가능합니다.");
		}
		lastChar++;
		return baseReply + lastChar;
	}
	
	// 파일업로드 정보 서버와 DB에 저장
	@Override
	public void saveFile(Write article, MultipartFile[] files, HttpServletRequest request) throws Exception {

		int fileIndex = 0;
		for(MultipartFile file : files) {
			if( !file.isEmpty() ) {
				fileIndex++;
				// 서버에 파일 저장
				String rootPath = request.getSession().getServletContext().getRealPath("/");
				String attachPath = "upload/";
				String fileName = file.getOriginalFilename();
				File f = new File(rootPath + attachPath + fileName);
				file.transferTo(f);
				// DB에 파일 정보 저장 - board_file 테이블, write 테이블
				BoardFile boardFile = new BoardFile();
				boardFile.setBoardId(article.getBoardId());
				boardFile.setWriteId(article.getId());
				boardFile.setNo(fileIndex);
				boardFile.setSource(fileName);
				boardFile.setFile(fileName);				// bf_file - 규칙성을 가지고 생성. 암호화?
				boardFile.setFilesize((int)file.getSize());
				boardFile = setDefaultValueTo(boardFile);
	
				fileRepository.save(boardFile);
			}
		}
	}
	
	// 글수정
	@Override
	public Write updateArticle(Write write) throws Exception {
		
		Write article = bbsRepository.findOne(write.getId());				// 수정할 글을 가져온다.
		if(article == null) 
			throw new FindException("데이터 변경에 실패하였습니다. 요청한 원글의 글번호를 확인해 주세요. 요청된 글 번호 : " + write.getId());
		
		article.setIp(CommonUtil.getIpAddress());							// 수정한 곳 IP
		article.setLast(CommonUtil.getToday(new Date()));					// 수정한 시간
		article.setName(write.getName());
		article.setEmail(write.getEmail());
		article.setHomepage(write.getHomepage());
		article.setOption(write.getOption());
		article.setCategoryName(write.getCategoryName());
		article.setSubject(write.getSubject());
		article.setContent(write.getContent());
		article.setLink11(write.getLink11());
		article.setLink12(write.getLink12());
		
		return bbsRepository.save(article);
	}

	// 원글 정보를 가지고 답변글 객체 생성
	@Override
	public Write createAnswerArticle(Write newAnswerArticle, Write baseArticle) throws FindException {
		int baseArticleId = baseArticle.getId();
		baseArticle = bbsRepository.findOne(baseArticleId);			// 원글 가져오기
		if(baseArticle == null) 
			throw new FindException("답변 글 생성을 위한 원글을 찾아오는데 실패하였습니다. 요청한 원글의 글번호를 확인해 주세요. 요청된 글 번호 : " + baseArticleId);
		 
		newAnswerArticle.setNum(baseArticle.getNum());						// num을 원글과 동일하게 
		newAnswerArticle.setSubject("Re: " + baseArticle.getSubject());
		newAnswerArticle.setContent(baseArticle.getContent());
		newAnswerArticle.setCategoryName(baseArticle.getCategoryName());
		newAnswerArticle.setOption(baseArticle.getOption());
		
		return newAnswerArticle;
	}

	// 글보기 정보 가져오기(이전글, 다음글, 글정보, 댓글 목록, 조회수 증가)
	@Override
	public Model getArticleView(Model model, int articleNumber, HttpServletRequest request, String boardName) throws FindException {
		
		setBoardId(boardName);												// boardId 세팅
		
		Write article = bbsRepository.findOne(articleNumber);
		int prevArticle = bbsEmRepository.findPrevOrNextArticle(articleNumber, "prev", this.boardId);
		int nextArticle = bbsEmRepository.findPrevOrNextArticle(articleNumber, "next", this.boardId);
		List<BoardFile> fileList = fileRepository.findByWriteIdAndBoardId(article.getId(), article.getBoardId());
		
		increaseHit(article, request);										// 조회수 증가 기능
		
		// 댓글 list를 가져온다.
		List<Write> commentList = bbsRepository.findByParentAndIsCommentOrderByCommentAscCommentReplyAsc(articleNumber, 1);
		
		model.addAttribute("article", article);
		model.addAttribute("fileList", fileList);
		model.addAttribute("prevArticle", prevArticle);
		model.addAttribute("nextArticle", nextArticle);
		model.addAttribute("commentList", commentList);
		
		return model;
	}
	
	// 조회수 증가(경우에 따라 증가시키지 않음)
	public void increaseHit(Write article, HttpServletRequest request) {
		
		String sessionName = "ARTICLE_VIEW_" + article.getBoardId() + "_" + article.getId();
		
		if(positiveIncreaseHitBySession(sessionName, article, request)) {
			article.setHit(article.getHit() + 1);
			bbsRepository.save(article);
			saveSession(sessionName, request);
		}

	}

	// 세션을 검사해서 조회수를 증가시킬 수 있는지 검사
	private boolean positiveIncreaseHitBySession(String sessionName, Write article, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute(sessionName) == null 
				|| (boolean) session.getAttribute(sessionName) == false) {
			return true;
		}
		return false;
	}

	// 조회수를 증가시킨 후 세션 저장
	private void saveSession(String sessionName, HttpServletRequest request) {
		request.getSession().setAttribute(sessionName, true);
	}

	// 글 목록에서 선택 삭제(댓글까지 삭제)
	@Override
	public int deleteArticleWithComment(String deleteIdString) {
		String deleteIds = findIdsWithCommentIds(deleteIdString);
		return bbsEmRepository.delete(deleteIds);						// 삭제한 게시물 수만큼 return
	}
	
	// 글 삭제할 때 글에 포함된 댓글까지 함께 지우기 위해 댓글 id들까지 함께 가져온다.
	public String findIdsWithCommentIds(String selectedId) {
		
		List<Write> idList = bbsEmRepository.findIdsWithCommentIds(selectedId);
		
		String result = "";
		int index = 0;
		int idListSize = idList.size();
		for(Write article : idList) {
			if(index != idListSize -1) {
				result += (article.getId() +",");
			} else {
				result += article.getId();
			}
			index++;
		}
		
		return result;
	}

	// 댓글 쓰기
	@Override
	public String insertComment(Write comment, BoardForm boardForm) throws Exception {

		// article : 원 글, comment : 댓글
		int originId = comment.getId();
		Write article = bbsRepository.findOne(originId);					// 원 글을 가져온다.
		Write baseComment = bbsRepository.findOne(boardForm.getBaseCommentId());	// 기준 댓글을 가져온다.
		
		comment.setId(bbsEmRepository.findMaxId()+1);						// 게시판에서 가장 큰 wr_id 가져오기
		comment.setNum(article.getNum());									// 원 글의 wr_num를 써줌으로 어떤 글의 댓글인지 표시한다.
		comment.setParent(article.getId());									// 원 글의 wr_id를 써줌으로 어떤 글의 댓글인지 표시한다.
		comment.setCategoryName(article.getCategoryName());					// 원 글의 카테고리 이름을 넣는다.
		comment.setIsComment(1);											// 댓글 1, 원글 0
		comment.setDatetime(new Date());
		comment.setLast(CommonUtil.getToday(new Date()));
		comment.setIp(CommonUtil.getIpAddress());
		comment.setFile(0);
		// 임시
		comment.setMemberId(comment.getName());					// (회원일 땐 세션에서 로그인한 ID 가져오기)
		comment.setBoardId(this.boardId);
		
		int commentGroup = appointComment(baseComment, article.getId());
		// 기준댓글(원글)의 comment_reply로 댓글의 commentReply를 만들어서 가져온다.
		String commentReply = createCommentReply(originId, commentGroup, baseComment);
		
		comment.setComment(commentGroup);
		comment.setCommentReply(commentReply);
		
		comment = bbsRepository.save(comment);								// 댓글 저장
	
		article.setComment(article.getComment() + 1);						// 원 글의 댓글 수(isComment) 수정
		bbsRepository.save(article);
		
		return commentReply;
	}

	// 댓글에 들어갈 comment를 지정
	public int appointComment(Write baseComment, int articleId) {
		int depth = baseComment.getCommentReply().length();					// 기준 댓글의 depth를 가져온다.
		if(depth == 0 && baseComment.getIsComment() == 0) {					// 기준이 원글인경우
			// 해당 글의 comment 중 가장 큰 값 + 1(comment단락을 나누기 위해)
			return bbsEmRepository.findMaxCommentById(articleId) + 1;	
		} else {
			// 기준 댓글의 comment그룹에 속하므로 값을 그대로 따라간다.
			return baseComment.getComment();
		}
	}
	
	// 작성할 댓글의 commentReply를 생성
	public String createCommentReply(int articleNumber, int comment, Write baseComment) {
		// ex) 	기준 댓글의 comment_reply : AA, AAA에 댓글이 존재함. 결과 : AAB 
		String baseCommentReply = baseComment.getCommentReply();			// 기준 댓글의 commentReply
		int length = baseCommentReply.length();								// 기준 댓글의 commentReply의 길이
		if(length == 0 && baseComment.getIsComment() == 0) {				// 기준이 원글일 경우				
			return "";
		} else {
			String maxCommentReply = bbsEmRepository.findMaxCommentReplyByBaseComment(articleNumber, comment, baseCommentReply, 1);
			if(baseCommentReply.equals(maxCommentReply)) {
				return baseCommentReply + "A";
			}
			char lastChar = maxCommentReply.charAt(maxCommentReply.length() - 1);
			if(lastChar == 'Z') {
				return "ERROR";
			}
			
			lastChar++;														// 마지막 문자 한자리 더함.
			return baseCommentReply + lastChar;
		}
	}

	// 댓글 수정
	@Override
	public void updateComment(Write comment, BoardForm boardForm) throws Exception {
		Write toUpdateComment = bbsRepository.findOne(boardForm.getBaseCommentId());
		toUpdateComment.setName(comment.getName());
		toUpdateComment.setContent(comment.getContent());
		toUpdateComment.setLast(CommonUtil.getToday(new Date()));
		toUpdateComment.setIp(CommonUtil.getIpAddress());
		
		bbsRepository.save(toUpdateComment);
	}

	// 댓글 삭제
	@Override
	public void deleteComment(Write comment, BoardForm boardForm) throws DeleteException {
		int delId = boardForm.getBaseCommentId();
		if(delId == 0) throw new DeleteException("삭제하려는 글이 존재하지 않습니다. 요청된 글 번호 : " + delId);
		bbsRepository.delete(delId);										// 댓글 삭제
		Write article = bbsRepository.findOne(comment.getId());				// 원글을 가져온다.
		article.setComment(article.getComment() - 1);						// 원글의 댓글 수(Comment) 수정
		bbsRepository.save(article);										// 원글 다시 저장
	}

	// 글 목록
	@Override
	public Model getListWithPaging(Model model, int pageNumber, String categoryName, String boardName) throws FindException {
		
		setBoardId(boardName);												// boardId 세팅
		
		int paramCurrentPage = pageNumber - 1;								// 현재 몇 페이지 인지	
		
		PageRequest pageRequest = new PageRequest(paramCurrentPage, pagePerPosts, new Sort(Direction.DESC, "id"));
		// 카테고리로 검색한 게시판 내용에 Paging, sorting 처리해서 가져오기 (댓글 제외)
		Page<Write> result = findByCategoryName(this.boardId, categoryName, pageRequest);
		logger.debug("게시판 글 목록에 페이징, 정렬, 댓글 제외, 카테고리 검색까지 적용 완료.");
		if(result == null) throw new FindException("게시판 내용을 찾을 수가 없습니다. 요청된 게시판 번호 : " + this.boardId);
		
		model = CommonUtil.pagingInfo(result, model);
		model.addAttribute("pagePerPosts", pagePerPosts);					// 한 페이지 당 게시물 수 
		model.addAttribute("currentCategory", categoryName);				// 현재 선택한 카테고리 이름
		
		List<String> categoryList = new ArrayList<String>(); 
		categoryList.add("all");
		categoryList.addAll(getCategoryList());								// 카테고리 가져오기
		model.addAttribute("categoryList", categoryList);
		
		return model;
	}
	
	// 게시판 가져오기 ( 카테고리로 검색 )
	public Page<Write> findByCategoryName(int boardId, String categoryName, PageRequest pageRequest) {
		if(categoryName.equals("all")) {
			return bbsRepository.findByBoardIdAndIsCommentOrderByNumAscReplyAsc(boardId, pageRequest, 0);
		} else {
			return bbsRepository.findByBoardIdAndCategoryNameAndIsCommentOrderByNumAscReplyAsc(boardId, categoryName, pageRequest, 0);
		}
	}

	// 카테고리 목록 가져오기
	@Override
	public List<String> getCategoryList() {
		return bbsEmRepository.findCategoryNames(this.boardId);
	} 

	// 게시판 마다 새글 게시물 5개씩 가져온다.
	// 필요한 정보 : 제목, 쓰여진지 하루? 이내면 new 표시, file 첨부 여부, link 여부
	@Override
	public Model getNewArticleList(Model model) {
		// 전체 게시판 목록을 가져온다.
		List<Board> boardList = boardRepository.findAll();
		List<List<Write>> newArticleList = new ArrayList<List<Write>>();
		for (Board board : boardList) {
			List<Write> articleList = bbsRepository.findTop5ByBoardIdAndIsCommentOrderByNumAsc(board.getId(), 0);
			newArticleList.add(articleList);
		}
		model.addAttribute("boardList", boardList);
		model.addAttribute("newArticleList", newArticleList);

		
		return model;
	}
	
	// 글 쓰기 - 입력값 외에 기본 값 셋팅
	public Write setDefaultValueTo(Write write) throws UnknownHostException {
		write.setParent(0);								// 일단 0 저장
		write.setDatetime(new Date());
		write.setLast(CommonUtil.getToday(new Date()));
		write.setIp(CommonUtil.getIpAddress());
		write.setBoardId(this.boardId);
		write.setCommentReply("");
		write.setFacebookUser("");
		write.setTwitterUser("");
		write.setExtra1("");
		write.setExtra2("");
		write.setExtra3("");
		write.setExtra4("");
		write.setExtra5("");
		write.setExtra6("");
		write.setExtra7("");
		write.setExtra8("");
		write.setExtra9("");
		write.setExtra10("");
		return write;
	}
	
	// 파일 업로드 - 입력값 외에 기본 값 셋팅
	public BoardFile setDefaultValueTo(BoardFile boardFile) {
		boardFile.setFileDatetime(new Date());
		boardFile.setDownload(0);
		boardFile.setContent("");
		boardFile.setFileWidth(0);
		boardFile.setFileHeight(0);
		boardFile.setFileType(0);
		return boardFile;
	}
	
}
