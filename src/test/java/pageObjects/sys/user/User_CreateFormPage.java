package pageObjects.sys.user;

import StepDefinitions.BaseClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class User_CreateFormPage extends BaseClass {

    public User_CreateFormPage() {

        PageFactory.initElements(getDriver(), this);
    }

    // Begin Find Elements

    @FindBy(xpath = "//sys_user_popupadd[1]/div[1]/div[1]/div[1]")
    WebElement lblHeader;

    @FindBy(xpath = "//sys_user_popupadd//span[contains(text(),'Bắt buộc')]")
    List<WebElement> lblRequireds;

    @FindBy(xpath = "//sys_user_popupadd//span[contains(text(),'Đã tồn tại')]")
    WebElement lblAlreadyExistValue;

    @FindBy(xpath = "//sys_user_popupadd[1]/div[1]/div[2]/mat-card[1]/div[1]/div[1]/cm_input[1]/mat-form-field[1]/div[1]/div[1]/div[1]/input[1]")
    WebElement txtFirsttName;

    @FindBy(xpath = "//sys_user_popupadd[1]/div[1]/div[2]/mat-card[1]/div[1]/div[2]/cm_input[1]/mat-form-field[1]/div[1]/div[1]/div[1]/input[1]")
    WebElement txtLastName;

    @FindBy(xpath = "//sys_user_popupadd[1]/div[1]/div[2]/mat-card[1]/div[2]/div[1]/cm_select[1]/mat-form-field[1]/div[1]/div[1]/div[1]/mat-select[1]")
    WebElement cboDept;

    @FindBy(xpath = "//sys_user_popupadd[1]/div[1]/div[2]/mat-card[1]/div[2]/div[2]/cm_select[1]/mat-form-field[1]/div[1]/div[1]/div[1]/mat-select[1]")
    WebElement cboJobt;

    @FindBy(xpath = "//ngx-mat-select-search[1]/div[1]/input[1]")
    WebElement optInput;

    @FindBy(xpath = "//mat-option[2]/span[1]")
    WebElement optValue;

    @FindBy(xpath = "//sys_user_popupadd[1]/div[1]/div[2]/mat-card[1]/div[3]/div[1]/cm_input[1]/mat-form-field[1]/div[1]/div[1]/div[1]/input[1]")
    WebElement txtUsername;

    @FindBy(xpath = "//sys_user_popupadd[1]/div[1]/div[2]/mat-card[1]/div[3]/div[2]/cm_input[1]/mat-form-field[1]/div[1]/div[1]/div[1]/input[1]")
    WebElement txtPassword;

    @FindBy(xpath = "//sys_user_popupadd//button[@transloco='close']")
    WebElement btnClose;

    @FindBy(xpath = "//sys_user_popupadd//button[@transloco='save']")
    WebElement btnSave;

    @FindBy(xpath = "//sys_user_popupadd//input[@readonly='true']")
    List<WebElement> readOnlyInput;

    @FindBy(xpath = "//sys_user_popupadd//input")
    List<WebElement> viewFormInput;

    @FindBy(xpath = "//sys_user_popupadd[1]/div[1]/div[2]/mat-card[1]/div[2]/div[1]/cm_select[1]/mat-form-field[1]/div[1]/div[1]/div[1]/mat-select[1]/div[1]/div[1]/span[1]/span[1]")
    WebElement cboDeptValue;
    @FindBy(xpath = "//sys_user_popupadd[1]/div[1]/div[2]/mat-card[1]/div[2]/div[2]/cm_select[1]/mat-form-field[1]/div[1]/div[1]/div[1]/mat-select[1]/div[1]/div[1]/span[1]/span[1]")
    WebElement cboJobtValue;


    public String verifyOpenCreateForm() {
        try {
            return lblHeader.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public void enterFirstName(String firstname ) {
        txtFirsttName.clear();
        sendKeys(getDriver(), txtFirsttName, firstname);
    }
    public void enterLastName(String lastname ) {
        txtLastName.clear();
        sendKeys(getDriver(), txtLastName, lastname);
    }
    public void enterUsername(String username ) {
        clearUsername();
        sendKeys(getDriver(), txtUsername, username);
    }
    public void enterPassword(String password ) {
        clearPassword();
        sendKeys(getDriver(), txtPassword, password);
    }


    public void clearUsername() {
        clickOn(getDriver(),txtUsername);
        txtUsername.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        txtUsername.sendKeys(Keys.chord(Keys.DELETE));
    }
    public void clearPassword() {
        clickOn(getDriver(),txtPassword);
        txtPassword.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        txtPassword.sendKeys(Keys.chord(Keys.DELETE));
    }

    public void chooseDept(String dept){
        clickOn(getDriver(), cboDept);
        sendKeys(getDriver(), optInput, dept);
        clickOn(getDriver(), optValue);
    }

    public void chooseJobt(String jobt){
        clickOn(getDriver(), cboJobt);
        sendKeys(getDriver(), optInput, jobt);
        clickOn(getDriver(), optValue);
    }

    public void clickOnSaveButton() {
        clickOn(getDriver(), btnSave);
    }
    public void clickOnCloseButton() {
        clickOn(getDriver(), btnClose);
    }

    public boolean verifyAlreadyExistUsername(String mes) {
        try {
            return mes.equalsIgnoreCase(lblAlreadyExistValue.getText());
        } catch (Exception e) {
            return false;
        }
    }
    public boolean verifyEquiredValues(String mes) {
        try {
            return mes.equalsIgnoreCase(lblRequireds.get(0).getText()) && mes.equalsIgnoreCase(lblRequireds.get(1).getText());
        } catch (Exception e) {
            return false;
        }
    }
    public boolean verifyReadOnlyFields(){
        return readOnlyInput.size() == 6;
    }

    public boolean showDefaultValuesForRead(List<String> values) {
        for (int i=0; i<viewFormInput.size() - 1; i++) {
            String content = viewFormInput.get(i).getAttribute("value");
            System.out.println("a: " + content + " - b: " +  values.get(i));
            if (!content.equalsIgnoreCase(values.get(i))) {
                return false;
            }
        }
        return true;
    }
    public boolean showDefaultValuesForUpdate(List<String> values) {

        String firstName = viewFormInput.get(0).getAttribute("value");
        String lastName = viewFormInput.get(1).getAttribute("value");
        String userName = viewFormInput.get(2).getAttribute("value");
        return firstName.equalsIgnoreCase(values.get(0))
                && lastName.equalsIgnoreCase(values.get(1))
                && userName.equalsIgnoreCase(values.get(4))
                && cboDeptValue.getText().equalsIgnoreCase(values.get(2))
                && cboJobtValue.getText().equalsIgnoreCase(values.get(3));
    }

    public void enterUserInfor(List<String> values) {
        enterFirstName(values.get(0));
        enterLastName(values.get(1));
        chooseDept(values.get(2));
        chooseJobt(values.get(3));

        clearUsername();
        clearPassword();

        if (values.size() == 5) {
            enterUsername(values.get(4));
        }
        else if (values.size() == 6) {
            enterUsername(values.get(4));
            enterPassword(values.get(5));
        }
    }
}
