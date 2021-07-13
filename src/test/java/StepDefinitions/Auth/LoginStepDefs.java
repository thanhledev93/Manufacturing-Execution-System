package StepDefinitions.Auth;

import StepDefinitions.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.Auth.LoginPage;


public class LoginStepDefs extends BaseClass {

    @Given("User Launch Chrome browser and User opens URL {string}")
    public void user_launch_chrome_browser_and_user_opens_url(String url) {
        loginPage = new LoginPage();
        driver.get(url);
    }

    @When("User enters Username as {string} and Password as {string}")
    public void user_enters_username_as_and_password_as(String username, String password) {
        loginPage.setUsername(username);
        loginPage.setPassword(password);
    }

    @And("Click on Login")
    public void click_on_login() {
        loginPage.clickLogin();
    }

    @Then("Page title Username is display")
    public void page_title_username_is_display() {
        WebElement ele = driver.findElement(By.xpath("//fuse-vertical-navigation[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]"));
        waitForElementByXpath("//fuse-vertical-navigation[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]");
        if (!ele.isDisplayed()) {
            Assert.assertTrue(false);
        } else {
            Assert.assertTrue(true);
        }
    }

    @When("User click on Log out link")
    public void user_click_on_log_out_link() {
        loginPage.clickLogout();
    }
    @Then("Form login title should be {string}")
    public void form_login_title_should_be(String title) {
        WebElement ele = driver.findElement(By.xpath("//auth-sign-in[1]/div[1]/div[1]/div[1]/div[2]"));
        Assert.assertEquals(title, ele.getText());
    }

    @And("close browser")
    public void close_browser() {
        driver.quit();
    }

//     Display Alert error When username or password is incorrect
    @And("Display error alert")
    public void display_error_alert() {

        WebElement ele = driver.findElement(By.xpath("//fuse-alert[1]/div[1]/div[1]/div[2]"));
        if (ele.isDisplayed()) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    //     Display Alert error When username or password is blank
    @When("User click on username and password without entering the value")
    public void user_click_on_username_and_password_without_entering_the_value() {
        loginPage.clickOnUsernameOrPasswordIsBlank();
    }

    @Then("show below error message textbox username and password")
    public void show_below_error_message_textbox_username_and_password() {
        loginPage.verifyWhenCLickUsernameOrPasswordBlank();
    }
}
