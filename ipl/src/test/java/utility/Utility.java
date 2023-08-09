package utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.AssumptionViolatedException;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import helper.WebHelper;
import services.WebServices;

public class Utility {
	private static String currentScenarioName = "";
	private static ArrayList<String> urlStatus = new ArrayList<String>();
	public static String path = "./TestData/OnesiteAPI.properties";
	
	public static void navigateToUrl(WebDriver driver, String url) {
		driver.get(url);
		//driver.manage().deleteAllcookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public static String getUrlAndCheckHealth(String component, String locale, ExtentTest extentTest) {
	 UrlVerifier urlVerifier = new UrlVerifier(component, locale);
		if (urlVerifier.isBroken()) {
		 System.out.println("Skipping the test. Reason: " + urlVerifier.getMessage());
		 extentTest.log(LogStatus.SKIP, "Skipping the test. Reason: " + urlVerifier.getMessage());
		 throw new AssumptionViolatedException("Skipping the test. Reason: " + urlVerifier.getMessage());
		 } else if (urlVerifier.isAlert()) {
		 System.out.println("Issue encountered while url health check: " + urlVerifier.getMessage() + "\r\nContinue...");
		 extentTest.log(LogStatus.WARNING, "Alert: " + urlVerifier.getMessage());
 } else {
		 System.out.println("Found healthy URL: " + urlVerifier.getURL());
		}

		 return urlVerifier.getURL();
		 }
	
	public static String randomStringGenerator(int no) {
		String randomno = RandomStringUtils.randomNumeric(no);
		return randomno;
	}
	
	public static void addUrlStatus(String _message) {
		urlStatus.add(currentScenarioName + " @ " + _message);
	}
	
	public static void navigateToUrl(WebDriver driver, String url, ExtentTest extentTest){
		 driver.get(url);
		 driver.manage().deleteAllCookies();
		 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		 driver.manage().window().maximize();
		 driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		 
		 extentTest.log(LogStatus.INFO, "I navigate to " + url);
		 }
	
	public static String getTestUrl(String component, String locale) throws FileNotFoundException {
		JSONParser parser = new JSONParser();
		String env = WebHelper.getValue(WebServices.ENV).toString();
		String testUrl = null;
		String path = "";
		System.out.println("getTestURL");
		 if (WebHelper.getValue(WebServices.SPRINT).equalsIgnoreCase("yes")) {
		 path = "/src/main/resources/testData/sprint/" + env + "/" + WebHelper.getValue(WebServices.TEAM).toLowerCase() + "TestData.json";

		} else {
		 path = "/src/main/resources/testData/regression/" + env + "/TestData.json";
		 //path = "/src/main/resources/testData/" + env + "/TestData.json";
}
		try {
		Object obj = parser.parse(new FileReader(
		 System.getProperty("user.dir") + path));
		 JSONObject jsonObject = (JSONObject) obj;
		 JSONArray componentUrl = (JSONArray) jsonObject.get(component);
		 for (int i = 0; i < componentUrl.size(); i++) {
		 JSONObject json1 = (JSONObject) componentUrl.get(i);
		 String loc = json1.get("locale").toString();
		 if (loc.equalsIgnoreCase(locale)) {
		 JSONArray urls = (JSONArray) json1.get("url");
		 JSONObject json2 = (JSONObject) urls.get(0);
		 testUrl = json2.get("1").toString();
		 System.out.println("Test Url: " + testUrl); 
		 }
		 }
	 } catch (ParseException e) {
		e.printStackTrace();
		 } catch (IOException e) {
		 e.printStackTrace();
		}
		 if(testUrl.contains("bcdzuoraqa.azurewebsites.net")){
		 return testUrl;
		 }else{
		 return testUrl + "?nocache=" + randomStringGenerator(7);
		 }
		 }
	
	public static void setCurrentScenarioName(String _currentScenarioName) {
		currentScenarioName = _currentScenarioName;
	}
	
	
}
