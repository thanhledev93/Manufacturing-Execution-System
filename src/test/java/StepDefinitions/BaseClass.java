package StepDefinitions;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import pageObjects.Auth.LoginPage;
import pageObjects.BaseObjectPage;
import pageObjects.sys.department.Department_CreateFormPage;
import pageObjects.sys.department.Department_MainTablePage;
import pageObjects.sys.department.Department_SearchFormPage;
import pageObjects.sys.job_title.JobTitle_CreateFormPage;
import pageObjects.sys.job_title.JobTitle_MainTablePage;
import pageObjects.sys.job_title.JobTitle_SearchFormPage;
import pageObjects.sys.user.User_CreateFormPage;
import pageObjects.sys.user.User_MainTablePage;
import pageObjects.sys.user.User_SearchFormPage;
import runner.WebDriverFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseClass extends WebDriverFactory {
    public static final String baseUrl = "https://quanlysanxuat.online/sign-in";
    public BaseObjectPage baseObjectPage;


    // Loging
    public LoginPage loginPage;

    // Department
    public Department_MainTablePage dept_mainTable;
    public Department_SearchFormPage dept_Search;
    public Department_CreateFormPage dept_createForm;

    // Job title
    public JobTitle_MainTablePage jobt_mainTable;
    public JobTitle_SearchFormPage jobt_search;
    public JobTitle_CreateFormPage jobt_create;

    // User
    public User_CreateFormPage user_create;
    public User_MainTablePage user_mainTable;
    public User_SearchFormPage user_search;


    //Create for generating random string for Unique email
    public String randomString() {
        String generatedString1 =  "/" + RandomStringUtils.randomAlphabetic(5);
        return (generatedString1);
    }
    public void waitForElementByXpath(String by) {
        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 10);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(by)));
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
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", dpkDate);

        clickOn(getDriver(), dpkDate);
        clickOn(getDriver(), chooseDate);
        clickOn(getDriver(), chY);
        clickOn(getDriver(), chM);

        while (true) {
            if (lblMonth.getText().equals(compareText)) {
                break;
            } else {
                clickOn(getDriver(), nextMonth);
            }
        }
        clickOn(getDriver(), chooseDay);
    }
    public void selectValueFromDropDown(WebElement ele, String value) {
        Select select = new Select(ele);
        select.selectByVisibleText(value);
        List<WebElement> listElements = select.getOptions();
        for (WebElement element : listElements) {
            if (value.equalsIgnoreCase(element.getText())) {
                clickOn(getDriver(), element);
                break;
            }
        }
    }



}
