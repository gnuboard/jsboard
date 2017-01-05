package kr.sir.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class Prefix {

	public String getTablePrefix() throws FileNotFoundException {
		Yaml yaml = new Yaml();
		InputStream io = new FileInputStream(new File("src/main/resources/config.yml"));
		
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) yaml.load(io);
		Object prefixObj = map.get("prefix");
		if(prefixObj != null)
			return prefixObj.toString();
		return "";
	}
}
