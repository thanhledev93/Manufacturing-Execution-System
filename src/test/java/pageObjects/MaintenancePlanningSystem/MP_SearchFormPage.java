package pageObjects.MaintenancePlanningSystem;

import StepDefinitions.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MP_SearchFormPage extends BaseClass {
    public static String verifyCboQualityCPValue;
    public MP_SearchFormPage() {
        PageFactory.initElements(getDriver(), this);
    }

    // Begin Find Elements
    @FindBy(xpath = "//maintenance_planning_index//mat-card/div[1]/div[1]//mat-select[1]")
    WebElement cboFact;

    @FindBy(xpath = "//maintenance_planning_index//mat-card/div[1]/div[2]//mat-select[1]")
    WebElement cboFactLine;

    @FindBy(xpath = "//maintenance_planning_index//mat-card/div[1]/div[3]//mat-select[1]")
    WebElement cboStatus;

    @FindBy(xpath = "//ngx-mat-select-search[1]/div[1]/input[1]")
    WebElement optInput;

    @FindBy(xpath = "//mat-option[2]/span[1]")
    WebElement optValue;

    @FindBy(xpath = "//ngx-mat-select-search[1]/div[contains(@class, 'mat-select-search-no-entries-found')]")
    WebElement optNoEntriesFound;

    @FindBy(xpath = "//maintenance_planning_index//input")
    WebElement txtSearch;

    @FindBy(xpath = "//maintenance_planning_index[1]//mat-card[1]/div[2]/button[@transloco='maintenance.search']")
    WebElement btnSearch;

    @FindBy(xpath = "//maintenance_planning_index[1]//mat-card[1]/div[2]/button[@transloco='maintenance.add']")
    WebElement btnAdd;

    // End Find Elements
    public void chooseFact(String factoryName) {
        clickOn(getDriver(), cboFact);
        sendKeys(getDriver(), optInput, factoryName);
        clickOn(getDriver(), optValue);
    }

    public void chooseFactLine(String factoryLineName)  {
        clickOn(getDriver(), cboFactLine);
        sendKeys(getDriver(), optInput, factoryLineName);
        clickOn(getDriver(), optValue);
    }

    public void chooseStatus(String approvalStatus){
        clickOn(getDriver(), cboStatus);
        sendKeys(getDriver(), optInput, approvalStatus);
        clickOn(getDriver(), optValue);
    }

    public void enterPlanNumber(String planNum) {
        clickOn(getDriver(), txtSearch);
        sendKeys(getDriver(), txtSearch, planNum);
    }
    public void clickOnSearchButton() {
        clickOn(getDriver(), btnSearch);
    }
    public void clickOnCreateButton() {
        clickOn(getDriver(), btnAdd);
    }

}
