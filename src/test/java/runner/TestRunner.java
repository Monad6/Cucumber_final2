package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


 @CucumberOptions(features ="src/main/resources/features" , glue = "stepsdef", plugin = {"pretty " , "html:target/reports/report.html"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
