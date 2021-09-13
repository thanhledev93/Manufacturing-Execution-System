package pageObjects.sys.user;

import StepDefinitions.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class User_MainTablePage extends BaseClass {
    public User_MainTablePage() {
        PageFactory.initElements(getDriver(), this);
    }

    // Begin Find Elements
    @FindBy(xpath = "//sys_user_index//div[2]//table")
    WebElement tblMainTable;

    @FindBy(xpath = "//table//tbody//tr[contains(@class, 'ng-star-inserted')]")
    List<WebElement> tblRows;

    @FindBy(xpath = "//button[@transloco='view']")
    WebElement btnView;

    @FindBy(xpath = "//button[@transloco='system.edit']")
    WebElement btnEdit;

    @FindBy(xpath = "//button[@transloco='system.delete']")
    WebElement btnDelete;

    @FindBy(xpath = "//select")
    WebElement slViewNumber;

    // End Find Elements
    public int getRowNumber() {
        return tblRows.size();
    }

    public void clickOnFeatureButtonInFirstRow() {
            WebElement btnFeature = tblMainTable.findElement(By.xpath("//sys_user_index[1]/div[1]/div[1]/div[1]/mat-card[2]/div[1]/div[1]/div[3]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/button[1]"));
            clickOn(getDriver(), btnFeature);
    }

    public void clickOnViewButton() {
        clickOn(getDriver(), btnView);
    }
    public void clickOnEditButton() {
        clickOn(getDriver(), btnEdit);
    }
    public void clickOnDeleteButton() {
        clickOn(getDriver(), btnDelete);
    }

    public boolean verifyUserIsDisplayedInTheList(String firstname, String lastname, String dept, String jobt, String username) throws InterruptedException {
        selectValueFromDropDown(slViewNumber, "100");
        Thread.sleep(1000);
        for (int i=1; i<=tblRows.size(); i++) {

            String tblFirstname = getDriver().findElement(By.xpath("//table//tbody//tr[contains(@class, 'ng-star-inserted')][" + i + "]//td[3]")).getText();
            String tblLastname = getDriver().findElement(By.xpath("//table//tbody//tr[contains(@class, 'ng-star-inserted')][" + i + "]//td[4]")).getText();
            String tblDept = getDriver().findElement(By.xpath("//table//tbody//tr[contains(@class, 'ng-star-inserted')][" + i + "]//td[5]")).getText();
            String tblJobt = getDriver().findElement(By.xpath("//table//tbody//tr[contains(@class, 'ng-star-inserted')][" + i + "]//td[6]")).getText();
            String tblUsername = getDriver().findElement(By.xpath("//table//tbody//tr[contains(@class, 'ng-star-inserted')][" + i + "]//td[7]")).getText();

            if (firstname.equalsIgnoreCase(tblFirstname) && lastname.equalsIgnoreCase(tblLastname) && dept.equalsIgnoreCase(tblDept) &&
                jobt.equalsIgnoreCase(tblJobt) && username.equalsIgnoreCase(tblUsername)) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyUserIsDisplayedInTheList(List<String> userInfors) throws InterruptedException {
        selectValueFromDropDown(slViewNumber, "100");
        Thread.sleep(1000);

        for (int i=1; i<=tblRows.size(); i++) {
            String tblFirstname = getDriver().findElement(By.xpath("//table//tbody//tr[contains(@class, 'ng-star-inserted')][" + i + "]//td[3]")).getText();
            String tblLastname = getDriver().findElement(By.xpath("//table//tbody//tr[contains(@class, 'ng-star-inserted')][" + i + "]//td[4]")).getText();
            String tblDept = getDriver().findElement(By.xpath("//table//tbody//tr[contains(@class, 'ng-star-inserted')][" + i + "]//td[5]")).getText();
            String tblJobt = getDriver().findElement(By.xpath("//table//tbody//tr[contains(@class, 'ng-star-inserted')][" + i + "]//td[6]")).getText();
            String tblUsername = getDriver().findElement(By.xpath("//table//tbody//tr[contains(@class, 'ng-star-inserted')][" + i + "]//td[7]")).getText();

            if (userInfors.get(0).equalsIgnoreCase(tblFirstname) &&
                    userInfors.get(1).equalsIgnoreCase(tblLastname) &&
                    userInfors.get(2).equalsIgnoreCase(tblDept) &&
                    userInfors.get(3).equalsIgnoreCase(tblJobt) &&
                    userInfors.get(4).equalsIgnoreCase(tblUsername)) {
                return true;
            }
        }
        return false;
    }
}
