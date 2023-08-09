package services;

import lombok.Getter;

public enum WebServices {

	WEB_URL("webUrl"),
	BROWSER("browser"),
	ENV("environment"),
	PLAN_ID("planId"),
	SUITE_ID("suiteID"),
	TEAM("team"),
	SPRINT("sprint");
	
	@Getter
	private final String basePathConfigkey;
	
	private WebServices (String basePathConfigkey) {
		this.basePathConfigkey = basePathConfigkey;
	}
	
	@Override
	public String toString() {
		return this.basePathConfigkey;
	}
}
