package runner;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    public static void setDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static WebDriver getDriver() { return webDriver.get(); }
    public static void setupDriver(String browserName) throws MalformedURLException {
        WebDriver driver = null;

        DesiredCapabilities cap = new DesiredCapabilities();
        if (browserName.equalsIgnoreCase("chrome")) {
            cap.setPlatform(Platform.ANY);
            cap.setBrowserName(BrowserType.CHROME);
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
        } else if (browserName.equalsIgnoreCase("firefox")) {
            cap.setPlatform(Platform.ANY);
            cap.setBrowserName(BrowserType.FIREFOX);
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "geckodriver.exe");
        }
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
        driver = new ChromeDriver();

        setDriver(driver);
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

}
