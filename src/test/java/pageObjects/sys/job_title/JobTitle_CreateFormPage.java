package pageObjects.sys.job_title;

import StepDefinitions.BaseClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class JobTitle_CreateFormPage extends BaseClass {

    public JobTitle_CreateFormPage() {

        PageFactory.initElements(getDriver(), this);
    }

    // Begin Find Elements

    @FindBy(xpath = "//body[1]/div[3]/div[2]/div[1]/mat-dialog-container[1]/sys_job_title_popupadd[1]/div[1]/div[1]/div[1]")
    WebElement lblHeader;

    @FindBy(xpath = "//body[1]/div[3]/div[2]/div[1]/mat-dialog-container[1]/sys_job_title_popupadd[1]/div[1]/div[2]/div[1]/div[1]/cm_input[1]/mat-form-field[1]/div[1]/div[1]/div[1]/span[1]/label[1]/mat-label[1]/span[1]/span[1]")
    WebElement lblRequiredName;

    @FindBy(xpath = "//body[1]/div[3]/div[2]/div[1]/mat-dialog-container[1]/sys_job_title_popupadd[1]/div[1]/div[2]/div[1]/div[1]/cm_input[1]/mat-form-field[1]/div[1]/div[1]/div[1]/input[1]")
    WebElement txtName;

    @FindBy(xpath = "//body[1]/div[3]/div[2]/div[1]/mat-dialog-container[1]/sys_job_title_popupadd[1]/div[1]/div[2]/div[1]/div[2]/cm_input[1]/mat-form-field[1]/div[1]/div[1]/div[1]/input[1]")
    WebElement txtNote;

    @FindBy(xpath = "//sys_job_title_popupadd[1]/div[1]/div[2]/div[2]/button[contains(@transloco, 'close')]")
    WebElement btnClose;

    @FindBy(xpath = "//sys_job_title_popupadd[1]/div[1]/div[2]/div[2]/button[contains(@transloco, 'save')]")
    WebElement btnSave;

    @FindBy(xpath = "//sys_job_title_popupadd//input[contains(readonly,true)]")
    List<WebElement> readOnlyInput;

    public String verifyOpenCreateForm() {
        try {
            return lblHeader.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public void clickOnCloseButton() {
        clickOn(getDriver(), btnClose);
    }

    public void enterName(String name ) {
        txtName.clear();
        sendKeys(getDriver(), txtName, name);
    }
    public void enterNote(String note ) {
        txtNote.clear();
        sendKeys(getDriver(), txtNote, note);
    }

    public void clearName() {
        clickOn(getDriver(),txtName);
        txtName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        txtName.sendKeys(Keys.chord(Keys.DELETE));
    }

    public void clickOnSaveButton() {
        clickOn(getDriver(), btnSave);
    }

    public boolean verifyNameEquired(String mes) {
        try {
            return mes.equalsIgnoreCase(lblRequiredName.getText());
        } catch (Exception e) {
            return false;
        }
    }
    public boolean verifyReadOnlyFields(){
        return readOnlyInput.size() == 2;
    }
    public boolean verifyLoadValue(String name, String note) {
        return readOnlyInput.get(0).getAttribute("value").equalsIgnoreCase(name) &&
               readOnlyInput.get(1).getAttribute("value").equalsIgnoreCase(note);
    }

}
