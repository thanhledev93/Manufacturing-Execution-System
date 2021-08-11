package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import java.io.File;
import java.io.IOException;

@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"json:target/cucumber.json"},
        dryRun = true,
        monochrome = true,
        glue = {"StepDefinitions"},
        tags = "@Integration"
)
public class RunCucumberTest extends AbstractTestNGCucumberTests{
    @BeforeSuite
    public static void setup() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c start " +System.getProperty("user.dir") + File.separator + ".github" + File.separator + "workflows" + File.separator + "start_dockergrid");
        Thread.sleep(30000);
    }

    @AfterSuite
    public static void tearDown() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c start " +System.getProperty("user.dir") + File.separator + ".github" + File.separator + "workflows" + File.separator + "stop_dockergrid");
        Thread.sleep(15000);
    }
}
