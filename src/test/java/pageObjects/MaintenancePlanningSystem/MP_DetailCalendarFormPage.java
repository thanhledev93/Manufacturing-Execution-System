package pageObjects.MaintenancePlanningSystem;

import StepDefinitions.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MP_DetailCalendarFormPage extends BaseClass {
    public static String verifyQualityCPSaveSuccess;
    public MP_DetailCalendarFormPage() {

        PageFactory.initElements(getDriver(), this);
    }

    // Begin Find Elements

    @FindBy(xpath = "//body[1]/div[3]/div[4]/div[1]/mat-dialog-container[1]/maintenance_planning_popupadd[1]/div[1]/div[1]/div[1]")
    WebElement lblHeader;

    @FindBy(xpath = "//maintenance_planning_popupadd[1]/div[1]/div[2]/mat-card[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]")
    WebElement tblDetailCalendar;

    @FindBy(xpath = "//mat-dialog-container[1]/maintenance_planning_popupadd[1]/div[1]/div[2]/mat-card[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[contains(@class, 'ng-star-inserted')]")
    List<WebElement> tbtRows;

    @FindBy(xpath = "//mat-dialog-container[1]/maintenance_planning_popupadd[1]/div[1]/div[2]/mat-card[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[contains(@class, 'ng-star-inserted')][1]//td")
    List<WebElement> tbtColumns;

    @FindBy(xpath = "//maintenance_planning_popupadd[1]/div[1]/div[1]/div[1]/span[1]/button[1]")
    WebElement btnCreateCalendar;

    @FindBy(xpath = "//body[1]/div[3]/div[4]/div[1]/mat-dialog-container[1]/maintenance_planning_popupadd[1]/div[1]/div[2]/div[1]/button[1]")
    WebElement btnClose;

    public int getTblRows() {
        return tbtRows.size();
    }

    public void clickOnCloseButton() {
        clickOn(getDriver(), btnClose);
    }

    public boolean verifyOpenMPForm() {
        try {
            return !lblHeader.isDisplayed();
        } catch (Exception e) {
            return true;
        }

    }

}
