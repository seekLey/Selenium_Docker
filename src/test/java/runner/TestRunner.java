package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/feature"},
        glue = {"stepDefinitions"},
        plugin = {"pretty","json:target/MyReports/cucumber.json"},
        publish = true,
        /*tags = {"@smoke"},*/ // This statement helps to run only scenarios which has this tag(Smoke)
        monochrome = true)

public class TestRunner {
}
