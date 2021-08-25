package pageObjects.sys.department;

import StepDefinitions.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Department_MainTablePage extends BaseClass {
    public Department_MainTablePage() {
        PageFactory.initElements(getDriver(), this);
    }

    // Begin Find Elements
    @FindBy(xpath = "//sys_department_index//div[2]//table")
    WebElement tblMainTable;

    @FindBy(xpath = "//table//tbody//tr[contains(@class, 'ng-star-inserted')]")
    List<WebElement> tblRows;

    @FindBy(xpath = "//button[@transloco='view']")
    WebElement btnView;

    @FindBy(xpath = "//button[@transloco='system.edit']")
    WebElement btnEdit;

    @FindBy(xpath = "//button[@transloco='system.delete']")
    WebElement btnDelete;

    @FindBy(xpath = "//button[contains(@transloco,'system.revert')]")
    WebElement btnRevert;

    @FindBy(xpath = "//sys_department_index//select")
    WebElement slViewNumber;

    // End Find Elements

    public void clickOnFeatureButtonInFirstRow() {
            WebElement btnFeature = tblMainTable.findElement(By.xpath("//sys_department_index[1]/div[1]/div[1]/div[1]/mat-card[2]/div[1]/div[1]/div[3]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/button[1]"));
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
    public void clickOnRevertButton() {
        clickOn(getDriver(), btnRevert);
    }

    public boolean verifyDepartmentIsDisplayedInTheList(String name, String note) throws InterruptedException {
        selectValueFromDropDown(slViewNumber, "100");
        Thread.sleep(1000);
        for (int i=1; i<=tblRows.size(); i++) {
            String tblname = getDriver().findElement(By.xpath("//table//tbody//tr[contains(@class, 'ng-star-inserted')][" + i + "]//td[3]")).getText();
            String tblnote = getDriver().findElement(By.xpath("//table//tbody//tr[contains(@class, 'ng-star-inserted')][" + i + "]//td[6]")).getText();
            System.out.println("name: " + name + " - note: " + note);

            if (name.equalsIgnoreCase(tblname) && note.equalsIgnoreCase(tblnote)) {
                return true;
            }
        }
        return false;
    }
}
