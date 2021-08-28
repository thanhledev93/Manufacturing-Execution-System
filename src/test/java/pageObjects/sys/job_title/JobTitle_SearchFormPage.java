package pageObjects.sys.job_title;

import StepDefinitions.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JobTitle_SearchFormPage extends BaseClass {
    public JobTitle_SearchFormPage() {
        PageFactory.initElements(getDriver(), this);
    }

    // Begin Find Elements

    @FindBy(xpath = "//sys_job_title_index//mat-select")
    WebElement cboStatus;

    @FindBy(xpath = "//ngx-mat-select-search[1]/div[1]/input[1]")
    WebElement optInput;

    @FindBy(xpath = "//mat-option[2]/span[1]")
    WebElement optValue;

    @FindBy(xpath = "//sys_job_title_index//input")
    WebElement txtSearch;

    @FindBy(xpath = "//sys_job_title_index//button[@transloco='system.search']")
    WebElement btnSearch;

    @FindBy(xpath = "//sys_job_title_index//button[@transloco='system.add']")
    WebElement btnAdd;

    // End Find Elements

    public void chooseStatus(String useStatus){
        clickOn(getDriver(), cboStatus);
        sendKeys(getDriver(), optInput, useStatus);
        clickOn(getDriver(), optValue);
    }

    public void enterJobTitleName(String name) {
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
