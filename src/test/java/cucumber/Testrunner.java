package cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    		glue = "com.example.stepdefinition",

    plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class Testrunner  extends AbstractTestNGCucumberTests {
	
	
	
}
