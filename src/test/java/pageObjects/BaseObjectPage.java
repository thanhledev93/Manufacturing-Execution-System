package pageObjects;

import StepDefinitions.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BaseObjectPage extends BaseClass {
    public BaseObjectPage() {
        PageFactory.initElements(getDriver(), this);
    }

    // Begin Find Elements
    @FindBy(xpath = "//h1")
    WebElement lblHeaderPage;

    @FindBy(xpath = "//h2[1]")
    WebElement alertBoxContains;

    @FindBy(xpath = "//body[1]/div[4]/div[1]/div[3]/button[1]")
    WebElement btnAlertBoxConfirm;

    @FindBy(xpath = "//body[1]/div[4]/div[1]/div[3]/button[contains(text(), 'Đóng') or contains(text(), 'OK') or contains(text(), 'Không')]")
    WebElement btnAlertBoxClose;

    //body[1]/div[3]/div[4]/div[1]/mat-dialog-container[1]
    @FindBy(xpath = "//body[1]/div[3]/div[4]/div[1]/mat-dialog-container[1]")
    WebElement frmApproval;

    @FindBy(xpath = "//body[1]/div[3]/div[4]/div[1]/mat-dialog-container[1]/cm_sys_approval_popup[1]/div[1]/div[1]/div[1]/cm_select[1]/mat-form-field[1]/div[1]/div[1]/div[1]/mat-select[1]")
    WebElement cboApproval;

    @FindBy(xpath = "//ngx-mat-select-search[1]/div[1]/input[1]")
    WebElement slOptionInput;

    @FindBy(xpath = "//mat-option[2]/span[1]")
    WebElement slOptionValue;

    @FindBy(xpath = "//body[1]/div[3]/div[4]/div[1]/mat-dialog-container[1]/cm_sys_approval_popup[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[contains(@class,'ng-star-inserted')]")
    List<WebElement> tblApprovalProcessRows;

    @FindBy(xpath = "//body[1]/div[3]/div[4]/div[1]/mat-dialog-container[1]/cm_sys_approval_popup[1]/div[2]/button[1]/span[1]")
    WebElement btnSubmitApproval;

    @FindBy(xpath = "//body[1]/div[3]/div[4]/div[1]/mat-dialog-container[1]/cm_sys_approval_popup[1]/div[2]/button[2]/span[1]")
    WebElement btnCloseApprovalForm;

    @FindBy(xpath = "//body[1]/div[4]/div[1]/div[2]/input[1]")
    WebElement txtReason;

    @FindBy(xpath = "//body[1]/div[4]/div[1]/div[3]/button[1]")
    WebElement btnReturn;



    public WebElement getHeaderPage() {
        return lblHeaderPage;
    }

    public boolean verifyAlertMes(String mes) {
        try {
            return mes.equalsIgnoreCase(alertBoxContains.getText());
        } catch (Exception e) {
            return false;
        }
    }
    public void clickOnCloseAlertMes() {
        clickOn(getDriver(), btnAlertBoxClose);
    }
    public void clickOnConfirmAlertMes() {
        clickOn(getDriver(), btnAlertBoxConfirm);
    }

    public boolean verifyCloseAlertMes() {
        try {
            return alertBoxContains.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAndChooseAprProcess(String apvProcess)  {
        clickOn(getDriver(), cboApproval);
        sendKeys(getDriver(), slOptionInput, apvProcess);
        clickOn(getDriver(), slOptionValue);
    }

    public boolean verifyDisplayArpProcessInfo() {
        return tblApprovalProcessRows.size() > 0;
    }
    public void clickOnSubmitApprovalButton() {
        clickOn(getDriver(), btnSubmitApproval);
    }

    public boolean verifyDisplayApprovalForm() {
        try {
            return frmApproval.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterReason(String reason) {
        sendKeys(getDriver(),txtReason,reason);
    }

    public void clickOnReturnButton() {
        clickOn(getDriver(), btnReturn);
    }


}
