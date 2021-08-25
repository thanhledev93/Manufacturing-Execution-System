package pageObjects.sys.department;

import StepDefinitions.BaseClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Department_CreateFormPage extends BaseClass {

    public Department_CreateFormPage() {

        PageFactory.initElements(getDriver(), this);
    }

    // Begin Find Elements

    @FindBy(xpath = "//sys_department_popupadd[1]/div[1]/div[1]/div[1]")
    WebElement lblHeader;

    @FindBy(xpath = "//sys_department_popupadd[1]/div[1]/div[2]/mat-card[1]/div[1]/div[1]/cm_input[1]/mat-form-field[1]/div[1]/div[1]/div[1]/input[1]")
    WebElement txtName;

    @FindBy(xpath = "//sys_department_popupadd[1]/div[1]/div[2]/mat-card[1]/div[1]/div[2]/cm_input[1]/mat-form-field[1]/div[1]/div[1]/div[1]/input[1]")
    WebElement txtNote;


    @FindBy(xpath = "//sys_department_popupadd[1]/div[1]/div[2]/mat-card[1]/div[1]/div[1]/cm_input[1]/mat-form-field[1]/div[1]/div[1]/div[1]/span[1]/label[1]/mat-label[1]/span[1]/span[1]")
    WebElement lblNameEquired;

    @FindBy(xpath = "//sys_department_popupadd[1]/div[1]/div[2]/div[1]/button[contains(@transloco, 'close')]")
    WebElement btnClose;

    @FindBy(xpath = "//sys_department_popupadd[1]/div[1]/div[2]/div[1]/button[contains(@transloco, 'save')]")
    WebElement btnSave;

    @FindBy(xpath = "//sys_department_popupadd//input[contains(readonly,true)]")
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

    public boolean verifyAlertMesForNameEquired(String mes) {
        try {
            return mes.equalsIgnoreCase(lblNameEquired.getText());
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
