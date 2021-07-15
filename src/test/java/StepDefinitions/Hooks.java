package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;




import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class Hooks extends BaseClass {
    @Before
    public void BeforeTest() {
        setupDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @AfterStep
    public void DelayAfterStep() throws InterruptedException {
        Thread.sleep(500);
    }

    @After
    public void AfterTest(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Error");
        }
        driver.quit();
    }
}
