package kr.sir.service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import kr.sir.domain.Write;
import kr.sir.domain.form.BoardForm;

public interface BbsService {
	
	// 카테고리 목록 가져오기
	public List<String> getCategoryList();

	// 글목록
	public Model getListWithPaging(Model model, int pageNumber, String categoryName, String boardName);
	
	// 글 목록에서 선택 삭제(댓글까지 삭제)
	public int deleteArticleWithComment(String deleteIdString);
	
	// 글쓰기
	public Write insertArticle(Write write, BoardForm boardForm, MultipartFile[] files) throws Exception;
	
	// 파일업로드 정보 서버와 DB에 저장
	public void saveFile(Write article, MultipartFile[] files, HttpServletRequest request) throws Exception;
	
	// 원글 정보를 가지고 답변글 객체 생성
	public Write createAnswerArticle(Write newAnswerArticle, Write baseArticle);

	// 글수정
	public Write updateArticle(Write write) throws Exception;
	
	// 게시글 보기
	public Write findOne(int articleNumber);

	// 글보기 정보 가져오기(이전글, 다음글, 글정보, 댓글 목록, 조회수 증가)
	public Model getArticleView(Model model, int articleNumber, HttpServletRequest request, String boardName);

	// 댓글 쓰기
	public String insertComment(Write comment, BoardForm boardForm) throws Exception;
	
	// 댓글 수정
	public void updateComment(Write comment, BoardForm boardForm) throws Exception;
	
	// 댓글 삭제
	public void deleteComment(Write comment, BoardForm boardForm);

	// (Index page) 게시판 마다 새글 게시물 5개씩 가져온다.
	public Model getNewArticleList(Model model);

}
