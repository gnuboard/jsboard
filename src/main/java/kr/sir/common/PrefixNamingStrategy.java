package kr.sir.common;

import java.io.FileNotFoundException;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class PrefixNamingStrategy extends PhysicalNamingStrategyStandardImpl{

	private static final long serialVersionUID = 1L;
    private static final ImprovedNamingStrategy STRATEGY_INSTANCE = new ImprovedNamingStrategy();
    
	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
		return new Identifier(classToTableName(name.getText()), name.isQuoted());
	}

	protected String classToTableName(String className) {
		String tablePrefix = "";
		try {
			tablePrefix = CommonUtil.getTablePrefix();
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다. "+ e.getMessage());
		}
		return STRATEGY_INSTANCE.classToTableName(tablePrefix + className);
	}

	

}
