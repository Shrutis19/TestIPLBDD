package helper;

import services.WebServices;
import utility.LoadProperties;

public class WebHelper {

	private static final String RUN_CONFIG_PROPERTIES = "/environment.properties";
	
	static {
		LoadProperties.loadRunConfigProps(RUN_CONFIG_PROPERTIES);
	}
	
	public static String getValue(WebServices requiredValue) {
		String val = LoadProperties.getRunProps().getProperty(requiredValue.getBasePathConfigkey());
		return val;
	}
}
