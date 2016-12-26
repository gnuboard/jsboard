package kr.sir.model.module;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class PrefixNamingStrategy extends PhysicalNamingStrategyStandardImpl{

	private static final String TABLE_PREFIX = "js5_";
	private static final long serialVersionUID = 1L;
    private static final ImprovedNamingStrategy STRATEGY_INSTANCE = new ImprovedNamingStrategy();
    
	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
		return new Identifier(classToTableName(name.getText()), name.isQuoted());
	}

	private String classToTableName(String className) {
		// TODO Auto-generated method stub
		return STRATEGY_INSTANCE.classToTableName(TABLE_PREFIX + className);
	}
	
}
