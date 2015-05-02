package ch.issueman.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * A wrapper for the typesafe config loader.
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConfigHelper {
	
	private final static Config config = ConfigFactory.load();
	
	/**
	 * Checks whether the String config entry exists or not and returns the defined value.
	 * 
	 * @param path the path in the config file.
	 * @param defaultcount the default value to return when the entry doesn't exist.
	 * @return the config value.
	 */
	public static String getConfig(String path, String defaultvalue) {
		if(config.hasPath(path)){
			return config.getString(path);
		}else{
			return defaultvalue;
		}
		
	}
	
	/**
	 * Checks whether the Integer config entry exists or not and returns the defined value.
	 * 
	 * @param path the path in the config file.
	 * @param defaultcount the default value to return when the entry doesn't exist.
	 * @return the config value.
	 */
	public static int getConfig(String path, int defaultvalue) {
		if(config.hasPath(path)){
			return config.getInt(path);
		}else{
			return defaultvalue;
		}
	}

	/**
	 * Checks whether the String List config entry exists or not and returns the defined values.
	 * 
	 * @param path the path in the config file.
	 * @param defaultvalues the default values for this config entry.
	 * @return the config String List.
	 */
	public static List<String> getConfig(String path, String[] defaultvalues) {
		if(config.hasPath(path)){
			return config.getStringList(path);
		}else{
			return new ArrayList<String>(Arrays.asList(defaultvalues));
		}
	}
}
