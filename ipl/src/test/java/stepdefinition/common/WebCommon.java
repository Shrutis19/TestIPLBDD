package stepdefinition.common;

import java.io.FileNotFoundException;

import helper.TestBaseWeb;
import io.cucumber.java.en.Given;
import utility.Utility;

public class WebCommon extends TestBaseWeb {
	
	String testUrl;
	
	@Given("I want to launch application for {string} in {string}")
	public void iNavigateIntoApplicationFor(String component, String locale) throws FileNotFoundException {
		testUrl = Utility.getUrlAndCheckHealth(component, locale, extentTest);
		Utility.navigateToUrl(driver, testUrl, extentTest);
	}
}
