package kr.sir.common;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "tables")
public class PrefixNamingStrategy extends PhysicalNamingStrategyStandardImpl{

	private String prefix;
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

//	private static final String TABLE_PREFIX = "js5_";
	private static final long serialVersionUID = 1L;
    private static final ImprovedNamingStrategy STRATEGY_INSTANCE = new ImprovedNamingStrategy();
    
	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
		return new Identifier(classToTableName(name.getText()), name.isQuoted());
	}

	private String classToTableName(String className) {
		// TODO Auto-generated method stub
		return STRATEGY_INSTANCE.classToTableName(getPrefix() + className);
	}
	
}
