package kr.sir.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.core.io.ClassPathResource;

public class CommonUtil {

	public static String getTablePrefix(){
		YamlMapFactoryBean yaml = new YamlMapFactoryBean();
		yaml.setResources(new ClassPathResource("config.yml"));
		
		Map<String, Object> map = yaml.getObject();

		Object prefixObj = map.get("prefix");
		if(prefixObj != null)
			return prefixObj.toString();
		
		return "";
	}
	
	public static String getToday(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return formatter.format(date);
	}
	
	public static String getIpAddress() throws UnknownHostException {
		InetAddress local = InetAddress.getLocalHost();
		return local.getHostAddress();
	}
	
}
