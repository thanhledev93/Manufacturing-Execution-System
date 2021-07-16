import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"json:target/cucumber.json"},
        dryRun = false,
        monochrome = true,
        glue = {"StepDefinitions"},
        tags = "@UnitTest"
)
public class RunCucumberTest {

}
