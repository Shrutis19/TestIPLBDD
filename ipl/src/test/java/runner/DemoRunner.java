package runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import helper.TestBaseWeb;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/web/component", 
		glue= {"stepdefinition","hooks"}, 
		monochrome = true,
		tags = "@ipl",
		plugin = {"pretty", 
				"html:target/cucumber-html-report.html",
				"json:target/cucumber-reports/cucumber.json",
				"junit:target/cucumber-reports/cucumber.xml",
				"rerun:target/rerun.txt"
				}
		)
public class DemoRunner extends TestBaseWeb{
/*	@BeforeClass
	public static void generateRunID() {
		AzureTestPlan.createPlanId();
	}
	
	@AfterClass
	public static void updateResult() {
		AzureTestPlan.updateAzureTestStatus(file);
	}
*/
}
