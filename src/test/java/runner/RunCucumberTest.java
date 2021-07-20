package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"json:target/cucumber.json"},
        dryRun = false,
        monochrome = true,
        glue = {"StepDefinitions"},
        tags = "@UnitTest"
)
public class RunCucumberTest extends AbstractTestNGCucumberTests{
//    @BeforeClass
//    public static void setup() throws IOException, InterruptedException {
//        System.out.println("@BeforeClass");
//        Runtime.getRuntime().exec("cmd /c start " +System.getProperty("user.dir") + File.separator + ".github" + File.separator + "workflows" + File.separator + "start_dockergrid.bat");
//        Thread.sleep(15000);
//    }
//
//    @AfterClass
//    public static void tearDown() throws IOException, InterruptedException {
//        System.out.println("@AfterClass");
//        Runtime.getRuntime().exec("cmd /c start " +System.getProperty("user.dir") + File.separator + ".github" + File.separator + "workflows" + File.separator + "stop_dockergrid.bat");
//        Thread.sleep(5000);
//    }
}
