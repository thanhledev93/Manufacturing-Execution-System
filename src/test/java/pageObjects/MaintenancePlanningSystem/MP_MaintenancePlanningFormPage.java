package pageObjects.MaintenancePlanningSystem;

import StepDefinitions.BaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MP_MaintenancePlanningFormPage extends BaseClass {
    public static String verifyMPSaveSuccess;
    public MP_MaintenancePlanningFormPage() {

        PageFactory.initElements(getDriver(), this);
    }

    // Begin Find Elements

    @FindBy(xpath = "//body[1]/div[3]/div[2]/div[1]/mat-dialog-container[1]/maintenance_planning_popupadd[1]/div[1]/div[1]/div[1]")
    WebElement lblHeader;

    @FindBy(xpath = "//mat-card[1]/div[1]/div[1]/cm_input[1]/mat-form-field[1]/div[1]/div[1]/div[1]/input[1]")
    WebElement txtPlanNum;

    @FindBy(xpath = "//maintenance_planning_popupadd[1]/div[1]/div[2]/mat-card[1]/div[2]/div[1]/cm_select[1]/mat-form-field[1]/div[1]/div[1]/div[1]/mat-select[1]")
    WebElement cboFactory;

    @FindBy(xpath = "//maintenance_planning_popupadd[1]/div[1]/div[2]/mat-card[1]/div[2]/div[2]/cm_select[1]/mat-form-field[1]/div[1]/div[1]/div[1]/mat-select[1]")
    WebElement cboFactoryLine;

    @FindBy(xpath = "//ngx-mat-select-search[1]/div[1]/input[1]")
    WebElement slOptionInput;

    @FindBy(xpath = "//mat-option[2]/span[1]")
    WebElement slOptionValue;

    @FindBy(xpath = "//maintenance_planning_popupadd[1]/div[1]/div[2]/mat-card[1]/div[3]/div[1]/cm_input[1]/mat-form-field[1]/div[1]/div[1]/div[1]/input[1]")
    WebElement txtNote;

    @FindBy(xpath = "//maintenance_planning_popupadd[1]/div[1]/div[2]/mat-card[2]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]")
    WebElement tblDevice;

    @FindBy(xpath = "//maintenance_planning_popupadd[1]/div[1]/div[2]/mat-card[2]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]//tbody//tr[contains(@class, 'ng-star-inserted')]")
    List<WebElement> tbtRows;

    @FindBy(xpath = "//maintenance_planning_popupadd[1]/div[1]/div[2]/mat-card[2]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]//tbody//tr[contains(@class, 'ng-star-inserted')][1]//td")
    List<WebElement> tbtColumns;

    @FindBy(xpath = "//maintenance_planning_popupadd[1]/div[1]/div[1]/div[1]/span[1]/button[1]")
    WebElement btnCreateCalendar;

    @FindBy(xpath = "//maintenance_planning_popupadd[1]/div[1]/div[2]/div[1]/button[1]")
    WebElement btnSave;

    @FindBy(xpath = "//maintenance_planning_popupadd[1]/div[1]/div[2]/div[1]/button[contains(text(), 'Đóng')]")
    WebElement btnClose;

    // Handle Datepicker - Choose 10/07/2021
    @FindBy(xpath = "//maintenance_planning_popupadd[1]/div[1]/div[2]/mat-card[1]/div[1]/div[2]/cm_input[1]/mat-form-field[1]/div[1]/div[1]/div[2]/mat-datepicker-toggle[1]")
    WebElement dpkCreatedDate;

    @FindBy(xpath = "//mat-card[2]//mat-datepicker-toggle[1]")
    List<WebElement> dpkExpectedDate;

    @FindBy(xpath = "//mat-month-view[1]/table[1]/tbody[1]/tr[1]/td[1]")
    WebElement lblMonth;

    @FindBy(xpath = "//mat-calendar-header[1]/div[1]/div[1]/button[1]")
    WebElement btnChooseDate;

    @FindBy(xpath = "//mat-multi-year-view[1]/table[1]/tbody[1]/tr[2]/td[2]/div[1]")
    WebElement btnChooseYear;

    @FindBy(xpath = "//mat-year-view[1]/table[1]/tbody[1]/tr[3]/td[3]/div[1]")
    WebElement btnChooseMonth;

    @FindBy(xpath = "//mat-month-view[1]/table[1]/tbody[1]/tr[2]/td[7]/div[1]")
    WebElement btnChooseDay;

    @FindBy(xpath = "//mat-calendar-header[1]/div[1]/div[1]/button[3]")
    WebElement btnNext;

    @FindBy(xpath = "//maintenance_planning_popupadd[1]//table[1]/tbody[1]/tr[1]/td[6]/button[1]")
    WebElement btnDetail;

    @FindBy(xpath = "//maintenance_planning_popupadd//input")
    List<WebElement> txtViewFormContents;

    @FindBy(xpath = "//maintenance_planning_popupadd[1]/div[1]/div[2]/mat-card[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody/tr")
    List<WebElement> tblDetailCalendarRows;


    @FindBy(xpath = "//maintenance_planning_popupadd//mat-label//span//span")
    WebElement lblAlertMes;

    @FindBy(xpath = "//maintenance_planning_popupadd//cm_sys_approval_button//button//span[contains(text(),'Gửi duyệt')]")
    WebElement btnSubmitApproval;

    @FindBy(xpath = "//maintenance_planning_popupadd//cm_sys_approval_button//button//span[contains(text(),'Duyệt')]")
    WebElement btnApproval;

    @FindBy(xpath = "//maintenance_planning_popupadd//cm_sys_approval_button//button//span[contains(text(),'Trả lại')]")
    WebElement btnReturn;



    public int getTblRows() {
        return tbtRows.size();
    }
    public int getTblDetailCalendarRows() {
        return tblDetailCalendarRows.size();
    }

    public boolean verifyOpenMPForm() {
        try {
            return lblHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean verifyReadOnly() {
        for (WebElement ele : txtViewFormContents) {
            if (ele.getAttribute("readonly") == null) {
                return false;
            }
        }
        return true;
    }

    public boolean showDefaultValues(List<String> values) {
        for (int i=0; i<txtViewFormContents.size(); i++) {
            String content = txtViewFormContents.get(i).getAttribute("value");
            if (!content.equalsIgnoreCase(values.get(i))) {
                System.out.println("a: " + content + " - b: " +  values.get(i));
                return false;
            }
        }
        return true;
    }

    public static String getVerifyMPSaveSuccess() {
        return verifyMPSaveSuccess;
    }

    public void clickOnCloseButton() {
        clickOn(getDriver(), btnClose);
    }

    public void clickOnDetailButton() {
        clickOn(getDriver(), btnDetail);
    }

    public void enterPlanNum() {
        verifyMPSaveSuccess = "";

        String content = "KHBT - " + randomString();

        txtPlanNum.clear();
        sendKeys(getDriver(), txtPlanNum, content);

        verifyMPSaveSuccess = content;
    }
    public void enterPlanNum(String planNum ) {
        txtPlanNum.clear();
        sendKeys(getDriver(), txtPlanNum, planNum);
    }
    public void enterNote() {
        String note = "Note - " + randomString();
        txtNote.clear();
        sendKeys(getDriver(), txtNote, note);
    }
    public void clearPlanNum() {
        clickOn(getDriver(),txtPlanNum);
        txtPlanNum.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        txtPlanNum.sendKeys(Keys.chord(Keys.DELETE));
    }

    public void chooseFact(String fact) throws InterruptedException {
        clickOn(getDriver(), cboFactory);
        sendKeys(getDriver(), slOptionInput, fact);

        Thread.sleep(1000);
        clickOn(getDriver(), slOptionValue);
    }

    public void chooseFactLine(String factLine) throws InterruptedException {
        clickOn(getDriver(), cboFactoryLine);
        sendKeys(getDriver(), slOptionInput, factLine);

        Thread.sleep(1000);
        clickOn(getDriver(), slOptionValue);
    }
    public void chooseCreatedDate() {
        String month = "JUL";
        chooseDatePicker(dpkCreatedDate, btnChooseDate, btnChooseYear,btnChooseMonth, lblMonth, btnChooseDay, btnNext, month);
    }
    public void chooseExpectedDate() throws InterruptedException {
        String month = "JUL";
        for (WebElement dpk : dpkExpectedDate) {
            Thread.sleep(100);
            chooseDatePicker(dpk, btnChooseDate, btnChooseYear,btnChooseMonth, lblMonth, btnChooseDay, btnNext, month);
        }
    }
    public void clickOnCreateCalendarButton() {
        clickOn(getDriver(), btnCreateCalendar);
    }

    public void clickOnSaveButton() {
        clickOn(getDriver(), btnSave);
    }
    public boolean verifyAlertMesForPlanNum(String mes) {
        try {
            return mes.equalsIgnoreCase(lblAlertMes.getText());
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOnSubAprButton() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", btnSubmitApproval);
        clickOn(getDriver(), btnSubmitApproval);
    }

    public void clickOnAprButton() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", btnApproval);
        clickOn(getDriver(), btnApproval);
    }

    public void clickOnReturnButton() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", btnReturn);
        clickOn(getDriver(), btnReturn);
    }

}
