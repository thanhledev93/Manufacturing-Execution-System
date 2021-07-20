package pageObjects.Auth;

import StepDefinitions.BaseClass;
import org.testng.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage extends BaseClass {

    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//mat-form-field[1]/div[1]/div[1]/div[1]/input[1]")
    @CacheLookup
    WebElement txtUsername;

    @FindBy(xpath = "//mat-form-field[2]/div[1]/div[1]/div[1]/input[1]")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(xpath = "//form[1]/button[1]")
    @CacheLookup
    WebElement btnLogin;


    @FindBy(xpath = "//user-menu[1]/button[1]/span[1]/span[1]/mat-icon[1]")
    @CacheLookup
    WebElement btnUser;

    @FindBy(xpath = "//body[1]/div[2]/div[2]/div[1]/div[1]/div[1]/button[5]")
    @CacheLookup
    WebElement btnLogout;

    @FindBy(xpath = "//mat-error[1]")
    @CacheLookup
    List<WebElement> msgEr;

    public void setUsername(String uname) {
        txtUsername.clear();
        sendKeys(getDriver(), txtUsername, uname);

    }


    public void setPassword(String pwd) {
        txtPassword.clear();
        sendKeys(getDriver(), txtPassword, pwd);
    }

    public void clickLogin() {
        clickOn(getDriver(), btnLogin);
    }

    public void clickLogout() {
        clickOn(getDriver(), btnUser);
        clickOn(getDriver(), btnLogout);
    }

    public void clickOnUsernameOrPasswordIsBlank()  {

        clickOn(getDriver(), txtUsername);
        txtUsername.sendKeys(Keys.CONTROL + "a");
        txtUsername.sendKeys(Keys.DELETE);

        clickOn(getDriver(), txtPassword);
        txtPassword.sendKeys(Keys.CONTROL + "a");
        txtPassword.sendKeys(Keys.DELETE);

        clickOn(getDriver(), txtUsername);

    }

    public void verifyWhenCLickUsernameOrPasswordBlank() {
        if (msgEr.size() != 2) {
            Assert.assertTrue(false);
        } else {
            Assert.assertTrue(true);
        }
    }

}
