package StepDefinitions;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.Auth.LoginPage;
import pageObjects.BaseObjectPage;
import pageObjects.MaintenancePlanningSystem.*;

import java.io.File;
import java.util.List;

public class BaseClass {
    public static final String baseUrl = "https://quanlysanxuat.online/sign-in";
    public static WebDriver driver;
    public LoginPage loginPage;

    // Maintenance Planning
    public MP_DetailCalendarFormPage mpDetailCalendar;
    public MP_MainTablePage mpMainTable;
    public MP_MaintenancePlanningFormPage mpMaintenancePlanning;
    public MP_SearchFormPage mpSearch;


    public BaseObjectPage baseObjectPage;


    //Create for generating random string for Unique email
    public String randomString() {
        String generatedString1 =  "/" + RandomStringUtils.randomAlphabetic(5);
        return (generatedString1);
    }
    public void waitForElementByXpath(String by) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(by)));
    }

    public void setupDriver(String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "geckodriver.exe");
            driver = new FirefoxDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
            driver = new ChromeDriver();
        }
    }

    public void sendKeys(WebDriver driver1, WebElement element, String value) {
        new WebDriverWait(driver1, 5).until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
    }

    public void clickOn(WebDriver driver1, WebElement element) {
        new WebDriverWait(driver1, 5).until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor jse = (JavascriptExecutor) driver1;
        jse.executeScript("arguments[0].click()", element);
    }

    public void chooseDatePicker(WebElement dpkDate, WebElement chooseDate, WebElement chY, WebElement chM,WebElement lblMonth,WebElement chooseDay, WebElement nextMonth, String compareText){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", dpkDate);

        clickOn(driver, dpkDate);
        clickOn(driver, chooseDate);
        clickOn(driver, chY);
        clickOn(driver, chM);

        while (true) {
            if (lblMonth.getText().equals(compareText)) {
                break;
            } else {
                clickOn(driver, nextMonth);
            }
        }
        clickOn(driver, chooseDay);
    }
    public void selectValueFromDropDown(WebElement ele, String value) {
        Select select = new Select(ele);
        select.selectByVisibleText(value);
        List<WebElement> listElements = select.getOptions();
        for (WebElement element : listElements) {
            if (value.equalsIgnoreCase(element.getText())) {
                clickOn(driver, element);
                break;
            }
        }
    }

}
