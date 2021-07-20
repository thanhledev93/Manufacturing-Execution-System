package StepDefinitions.MaintenancePlanningSystem;

import StepDefinitions.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.openqa.selenium.support.ui.FluentWait;
import pageObjects.BaseObjectPage;
import pageObjects.MaintenancePlanningSystem.MP_DetailCalendarFormPage;
import pageObjects.MaintenancePlanningSystem.MP_MainTablePage;
import pageObjects.MaintenancePlanningSystem.MP_MaintenancePlanningFormPage;
import pageObjects.MaintenancePlanningSystem.MP_SearchFormPage;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;


public class CreateMPStepDefs extends BaseClass {
    public CreateMPStepDefs() {
        mpSearch = new MP_SearchFormPage();
        mpMainTable = new MP_MainTablePage();
        mpMaintenancePlanning = new MP_MaintenancePlanningFormPage();
        mpDetailCalendar = new MP_DetailCalendarFormPage();
        baseObjectPage = new BaseObjectPage();
    }

    // Scenario: Create maintenance planning with valid Value.***********************************
    @When("User click on create maintenance planning button")
    public void user_click_on_create_maintenance_planning_button() {
        mpSearch.clickOnCreateButton();
    }

    @When("User enter maintenance planning number")
    public void user_enter_maintenance_planning_number() {
        mpMaintenancePlanning.enterPlanNum();
    }
    @When("User click on factory field and choose factory as {string} in maintenance planning form")
    public void user_click_on_factory_field_and_choose_factory_as_in_maintenance_planning_form(String fact) throws InterruptedException {
        mpMaintenancePlanning.chooseFact(fact);
    }
    @When("User click on factory line field and choose factory line as {string} in maintenance planning form")
    public void user_click_on_factory_line_field_and_choose_factory_line_as_in_maintenance_planning_form(String factLine) throws InterruptedException {
        mpMaintenancePlanning.chooseFactLine(factLine);
    }
    @When("User enter note")
    public void user_enter_note() {
        mpMaintenancePlanning.enterNote();
    }
    @When("User change created date")
    public void user_change_created_date() {
        mpMaintenancePlanning.chooseCreatedDate();
    }
    @When("User change expected date of devices")
    public void user_change_expected_date_of_devices() throws InterruptedException {
        mpMaintenancePlanning.chooseExpectedDate();
    }
    @When("User click on create calendar button")
    public void user_click_on_create_calendar_button() {
        mpMaintenancePlanning.clickOnCreateCalendarButton();
    }

    @Then("Display alert message as {string}")
    public void display_alert_message_as(String mes) {
        boolean isDisplay = baseObjectPage.verifyAlertMes(mes);
//        Assert.assertTrue("The message is not displayed", isDisplay);
    }
    @When("User click on yes button")
    public void user_click_on_yes_button() {
        baseObjectPage.clickOnConfirmAlertMes();
    }
    @When("User click on confirm button")
    public void user_click_on_confirm_button() {
        baseObjectPage.clickOnCloseAlertMes();
    }
    @Then("Close alert message")
    public void close_alert_message() {
//        await().atMost(5000, TimeUnit.SECONDS).untilAsserted(()
//                -> Assert.assertFalse("Can't closed popup", baseObjectPage.verifyCloseAlertMes()));
    }

    @When("User click on save button in maintenance planning form")
    public void user_click_on_save_button_in_maintenance_planning_form() {
        mpMaintenancePlanning.clickOnSaveButton();
    }
    @Then("User should found maintenance planning in the table")
    public void user_should_found_maintenance_planning_in_the_table() throws InterruptedException {
        int isContent = mpMainTable.getRowIndex(mpMaintenancePlanning.getVerifyMPSaveSuccess());
//        Assert.assertTrue("Not Found maintenance planning", isContent != -1);
    }

    // Scenario: Create maintenance planning with required values is blank ***********************************
    @When("User not enter plan number")
    public void user_not_enter_plan_number() {
        mpMaintenancePlanning.clearPlanNum();
    }
    @Then("Display alert message as {string} for required values in maintenance planning form")
    public void display_alert_message_as_for_required_values_in_maintenance_planning_form(String mes) {
        boolean isDisplay = mpMaintenancePlanning.verifyAlertMesForPlanNum(mes);
//        Assert.assertTrue("Can't display alert message", isDisplay);
    }

    // Scenario: Create maintenance planning with plan number already exist ***********************************
    @Given("There is a maintenance plan with plan number as {string}")
    public void there_is_a_maintenance_plan_with_plan_number_as(String planNum) {
        mpSearch.enterPlanNumber(planNum);
        mpSearch.clickOnSearchButton();
//        await().atMost(5000, TimeUnit.SECONDS).untilAsserted(()
//                -> Assert.assertTrue("Not found maintenance planning", mpMainTable.getNoOfRows() > 0));
    }
    @When("User enter maintenance planning number with plan number as {string}")
    public void user_enter_maintenance_planning_number_with_plan_number_as(String planNum) {
       mpMaintenancePlanning.enterPlanNum(planNum);
    }

    // Scenario: Create failed when canceling create operation ***********************************

    @Then("User should not found maintenance planning in the table")
    public void user_should_not_found_maintenance_planning_in_the_table() throws InterruptedException {
        int isContent = mpMainTable.getRowIndex(mpMaintenancePlanning.getVerifyMPSaveSuccess());
//        Assert.assertTrue("Not Found maintenance planning", isContent == -1);
    }

}
