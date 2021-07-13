package pageObjects.MaintenancePlanningSystem;

import StepDefinitions.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MP_MainTablePage extends BaseClass {
    public static String verifyContentValue = "";
    public static int index = -1;
    public MP_MainTablePage() {
        PageFactory.initElements(driver, this);
    }

    // Begin Find Elements
    @FindBy(xpath = "//maintenance_planning_index//div[2]//table")
    WebElement tblMainTable;

    @FindBy(xpath = "//table//tbody//tr[contains(@class, 'ng-star-inserted')]")
    List<WebElement> tblRows;

    @FindBy(xpath = "//button[@transloco='view']")
    WebElement btnView;

    @FindBy(xpath = "//button[@transloco='edit']")
    WebElement btnEdit;

    @FindBy(xpath = "//button[@transloco='delete']")
    WebElement btnDelete;

    @FindBy(xpath = "//maintenance_planning_index//select")
    WebElement slViewNumber;


    // End Find Elements

    public int getNoOfRows() {
        return tblRows.size();
    }


    public void clickOnFeatureButton() {
        verifyContentValue = tblMainTable.findElement(By.xpath("//maintenance_planning_index//table//tbody//tr[" + index + "]//td[3]")).getText();
        System.out.println(verifyContentValue);
        WebElement btnFeature = tblMainTable.findElement(By.xpath("//maintenance_planning_index//table//tbody//tr[" + index + "]//td//button[@transloco='system.feature']"));
        clickOn(driver, btnFeature);
    }

    public void clickOnFeatureButtonInFirstRow() {
        verifyContentValue = tblMainTable.findElement(By.xpath("//maintenance_planning_index//table//tbody//tr[1]//td[3]")).getText();
        System.out.println(verifyContentValue);
        WebElement btnFeature = tblMainTable.findElement(By.xpath("//maintenance_planning_index//button[@transloco='maintenance.feature']"));
        clickOn(driver, btnFeature);
    }

    public void clickOnViewButton() {
        clickOn(driver, btnView);
    }
    public void clickOnEditButton() {
        clickOn(driver, btnEdit);
    }
    public void clickOnDeleteButton() {
        clickOn(driver, btnDelete);
    }

    public List<WebElement> getTblRows() {
        selectValueFromDropDown(slViewNumber, "100");
        return tblRows;
    }

    public int getRowIndex() {
        selectValueFromDropDown(slViewNumber, "100");
        for (int i=1; i<=tblRows.size(); i++) {
            String ctn = driver.findElement(By.xpath("//table//tbody//tr[contains(@class, 'ng-star-inserted')][" + i + "]//td[3]")).getText();
            if (verifyContentValue.equalsIgnoreCase(ctn)) {
                System.out.println("Test - " + verifyContentValue);
                return i;
            }
        }
        return -1;
    }

    public int getRowIndex(String content) throws InterruptedException {
        selectValueFromDropDown(slViewNumber, "100");
        Thread.sleep(1000);
        for (int i=1; i<=tblRows.size(); i++) {
            String ctn = driver.findElement(By.xpath("//table//tbody//tr[contains(@class, 'ng-star-inserted')][" + i + "]//td[3]")).getText();
            if (content.equalsIgnoreCase(ctn)) {
                return index = i;
            }
        }
        return -1;
    }

    public boolean verifyApprovalStatus(String ts) {
        System.out.println(getRowIndex());
        try {
            WebElement element = driver.findElement(By.xpath("//maintenance_planning_index[1]/div[1]/div[1]/div[1]/mat-card[2]/div[1]/div[1]/div[3]/div[2]/table[1]/tbody[1]/tr[" + getRowIndex() + "]/td[7]//span[contains(text(),'"+ ts +"')]"));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
