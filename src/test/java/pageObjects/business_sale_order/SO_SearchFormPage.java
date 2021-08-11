package pageObjects.business_sale_order;

import StepDefinitions.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SO_SearchFormPage extends BaseClass {
    public SO_SearchFormPage() {
        PageFactory.initElements(getDriver(), this);
    }

    // Begin Find Elements
    @FindBy(xpath = "//business_sale_order_index//mat-card/div[1]/div[1]//mat-select[1]")
    WebElement cboCus;

    @FindBy(xpath = "//business_sale_order_index//mat-card/div[1]/div[3]//mat-select[1]")
    WebElement cboStatus;

    @FindBy(xpath = "//ngx-mat-select-search[1]/div[1]/input[1]")
    WebElement optInput;

    @FindBy(xpath = "//mat-option[2]/span[1]")
    WebElement optValue;

    @FindBy(xpath = "//business_sale_order_index//input")
    WebElement txtSearch;

    @FindBy(xpath = "//business_sale_order_index[1]//mat-card[1]/div[2]/button[@transloco='search']")
    WebElement btnSearch;

    @FindBy(xpath = "//business_sale_order_index[1]//mat-card[1]/div[2]/button[@transloco='add']")
    WebElement btnAdd;

    // End Find Elements
    public void chooseCustomer(String cusName) {
        clickOn(getDriver(), cboCus);
        sendKeys(getDriver(), optInput, cusName);
        clickOn(getDriver(), optValue);
    }

    public void chooseStatus(String approvalStatus){
        clickOn(getDriver(), cboStatus);
        sendKeys(getDriver(), optInput, approvalStatus);
        clickOn(getDriver(), optValue);
    }

    public void enterSONumber(String soNum) {
        clickOn(getDriver(), txtSearch);
        sendKeys(getDriver(), txtSearch, soNum);
    }
    public void clickOnSearchButton() {
        clickOn(getDriver(), btnSearch);
    }
    public void clickOnCreateButton() {
        clickOn(getDriver(), btnAdd);
    }

}
