package StepDefinitions.MaintenancePlanningSystem;


import StepDefinitions.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.awaitility.Durations;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pageObjects.Auth.LoginPage;
import pageObjects.MaintenancePlanningSystem.MP_MainTablePage;
import pageObjects.MaintenancePlanningSystem.MP_SearchFormPage;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.*;
import static org.awaitility.Durations.*;


public class SearchMPStepDefs extends BaseClass {
    public SearchMPStepDefs() {
        loginPage = new LoginPage();
        mpSearch = new MP_SearchFormPage();
        mpMainTable = new MP_MainTablePage();
    }

//    Scenario: User found MP in the table when choosing factory and factory line and approval status valid
    @Given("User is on {string} page with account {string}")
    public void user_is_on_page_with_account(String page, String userName) {
       getDriver().get(baseUrl);
       loginPage.setUsername(userName);
       loginPage.setPassword("123");
       loginPage.clickLogin();

        WebElement mnu = getDriver().findElement(By.xpath("//span[contains(text(),'"+ page + "')]//ancestor::a"));

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", mnu);
        clickOn(getDriver(), mnu);
    }

    @When("User click on factory field and choose factory as {string}")
    public void user_click_on_factory_field_and_choose_factory_as(String fact) {
        try {
            mpSearch.chooseFact(fact);
        } catch (Exception e) {
            Assert.assertTrue(false, "Can't select factory");
        }
    }
    @When("User click on factory line field and choose factory line as {string}")
    public void user_click_on_factory_line_field_and_choose_factory_line_as(String factLine) {
        try {
            mpSearch.chooseFactLine(factLine);
        } catch (Exception e) {
            Assert.assertTrue(false, "Can't select factory line");
        }
    }
    @When("User click on approval status field and choose status as {string}")
    public void user_click_on_approval_status_field_and_choose_status_as(String status) {
        try {
            mpSearch.chooseStatus(status);
        } catch (Exception e) {
            Assert.assertTrue(false, "Can't select approval status");
        }
    }
    @Then("User should found maintenance planning in the table when searching")
    public void user_should_found_maintenance_planning_in_the_table_when_searching() {
        await().atMost(5000, TimeUnit.SECONDS).untilAsserted(()
                -> Assert.assertEquals(mpMainTable.getNoOfRows() > 0, true, "Not found maintenance planning"));
//        Assert.assertTrue(, true);
    }

//    Scenario: User not found MP in the table when enter planning number inValid.
    @When("User click on Search field and enter planning number as {string}")
    public void user_click_on_search_field_and_enter_planning_number_as(String planNumber) {
        mpSearch.enterPlanNumber(planNumber);
    }

    @When("User click on search button")
    public void user_click_on_search_button() {
        mpSearch.clickOnSearchButton();
    }

    @Then("User should not found maintenance planning in the table when searching")
    public void user_should_not_found_maintenance_planning_in_the_table_when_searching() {
        await().atMost(5000, TimeUnit.SECONDS).untilAsserted(()
                -> Assert.assertEquals( 0, mpMainTable.getNoOfRows(),"Found maintenance planning"));
        System.out.println(mpMainTable.getNoOfRows());
//        Assert.assertTrue("Found maintenance planning", mpMainTable.getNoOfRows() == 0);
    }

}
