package kr.sir.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class CommonUtil {

	public static String getTablePrefix() throws FileNotFoundException {
		Yaml yaml = new Yaml();
		InputStream io = new FileInputStream(new File("src/main/resources/config.yml"));

		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) yaml.load(io);
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
