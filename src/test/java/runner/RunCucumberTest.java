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
        dryRun = false,
        monochrome = true,
        glue = {"StepDefinitions"},
        tags = "@UnitTest"
)
public class RunCucumberTest extends AbstractTestNGCucumberTests{
    @BeforeSuite
    public static void setup() throws IOException, InterruptedException {
        System.out.println("setup() running");
        Runtime.getRuntime().exec("cmd /c start " +System.getProperty("user.dir") + File.separator + ".github" + File.separator + "workflows" + File.separator + "start_dockergrid.bat");
        Thread.sleep(60000);
    }

    @AfterSuite
    public static void tearDown() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("cmd /c start " +System.getProperty("user.dir") + File.separator + ".github" + File.separator + "workflows" + File.separator + "stop_dockergrid.bat");
        Thread.sleep(15000);
    }
}
