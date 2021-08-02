package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import runner.WebDriverFactory;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class Hooks extends BaseClass {
    @Before
    public void setup() throws MalformedURLException {
        String browserName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browserName");
        WebDriverFactory.setupDriver(browserName);
    }
    @AfterStep
    public void DelayAfterStep() throws InterruptedException {
        Thread.sleep(500);
    }

    @After
    public void AfterTest(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Error");
        }
        getDriver().quit();
    }
}
