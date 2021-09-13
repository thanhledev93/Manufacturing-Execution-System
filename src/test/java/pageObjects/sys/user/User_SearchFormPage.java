package pageObjects.sys.user;

import StepDefinitions.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class User_SearchFormPage extends BaseClass {
    public User_SearchFormPage() {
        PageFactory.initElements(getDriver(), this);
    }

    // Begin Find Elements

    @FindBy(xpath = "//sys_user_index[1]/div[1]/div[1]/div[1]/mat-card[1]/div[1]/div[1]/mat-form-field[1]/div[1]/div[1]/div[1]/input[1]")
    WebElement txtSearch;

    @FindBy(xpath = "//sys_user_index[1]/div[1]/div[1]/div[1]/mat-card[1]/div[2]/button[1]")
    WebElement btnSearch;

    @FindBy(xpath = "//sys_user_index[1]/div[1]/div[1]/div[1]/mat-card[1]/div[2]/button[2]")
    WebElement btnAdd;

    // End Find Elements

    public void enterUserName(String name) {
        clickOn(getDriver(), txtSearch);
        sendKeys(getDriver(), txtSearch, name);
    }
    public void clickOnSearchButton() {
        clickOn(getDriver(), btnSearch);
    }
    public void clickOnCreateButton() {
        clickOn(getDriver(), btnAdd);
    }

}
