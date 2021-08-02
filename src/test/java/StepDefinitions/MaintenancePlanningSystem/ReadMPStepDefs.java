package StepDefinitions.MaintenancePlanningSystem;

import StepDefinitions.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.BaseObjectPage;
import pageObjects.MaintenancePlanningSystem.MP_DetailCalendarFormPage;
import pageObjects.MaintenancePlanningSystem.MP_MainTablePage;
import pageObjects.MaintenancePlanningSystem.MP_MaintenancePlanningFormPage;
import pageObjects.MaintenancePlanningSystem.MP_SearchFormPage;

import java.util.List;


public class ReadMPStepDefs extends BaseClass {
    public ReadMPStepDefs() {
        mpSearch = new MP_SearchFormPage();
        mpMainTable = new MP_MainTablePage();
        mpMaintenancePlanning = new MP_MaintenancePlanningFormPage();
        mpDetailCalendar = new MP_DetailCalendarFormPage();
        baseObjectPage = new BaseObjectPage();
    }
    @Given("User has maintenance planning as {string}")
    public void user_has_maintenance_planning_as(String planNum) {
            mpSearch.enterPlanNumber(planNum);
            mpSearch.clickOnSearchButton();
            Assert.assertTrue(mpMainTable.getNoOfRows() > 0, "Not found maintenance planning");
    }

    @When("User choose maintenance planning and click on feature button and choose view")
    public void user_choose_maintenance_planning_and_click_on_feature_button_and_choose_view() {
        mpMainTable.clickOnFeatureButtonInFirstRow();
        mpMainTable.clickOnViewButton();
    }
    @Then("Open maintenance planning form")
    public void open_maintenance_planning_form() {
        boolean isFormDisplay = mpMaintenancePlanning.verifyOpenMPForm();
        Assert.assertTrue(isFormDisplay, "Maintenance planning form not opened");
    }
    @Then("Maintenance planning form fields are read-only")
    public void maintenance_planning_form_fields_are_read_only() {
        boolean isReadOnly = mpMaintenancePlanning.verifyReadOnly();
        Assert.assertTrue(isReadOnly, "There is a field that is not assigned read-only attribute");
    }

    @Then("Maintenance planning form fields are loaded by default")
    public void maintenance_planning_form_fields_are_loaded_by_default(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> dTbl = dataTable.asLists();
        boolean isLoad = mpMaintenancePlanning.showDefaultValues(dTbl.get(0));
        boolean tblDataLoaded = mpMaintenancePlanning.getTblRows() == 4;
        Assert.assertTrue(isLoad && tblDataLoaded, "Value of fields are not loaded by default");
    }
    @When("User click on detail button")
    public void user_click_on_detail_button() {
        mpMaintenancePlanning.clickOnDetailButton();
    }

    @Then("Open detail calendar form and form fields are loaded by default")
    public void open_detail_calendar_form_and_form_fields_are_loaded_by_default() {
       boolean tblDataLoaded = mpDetailCalendar.getTblRows() == 7;
       Assert.assertTrue(tblDataLoaded, "Detail calendar form fields are loaded by default");
    }
    @When("User click on close button in detail calendar form")
    public void user_click_on_close_button_in_detail_calendar_form() {
       mpDetailCalendar.clickOnCloseButton();
    }

    @Then("Close detail calendar form")
    public void close_detail_calendar_form() throws InterruptedException {
        Thread.sleep(1000);
        boolean isCloseDCForm = mpDetailCalendar.verifyOpenMPForm();
        Assert.assertTrue(isCloseDCForm, "Detail calendar form is not closed");
    }

    @When("User click on close button in maintenance planning form")
    public void user_click_on_close_button_in_maintenance_planning_form() {
        mpMaintenancePlanning.clickOnCloseButton();
    }
    @Then("Close maintenance planning form")
    public void close_maintenance_planning_form() {
        try {
            Assert.assertTrue(baseObjectPage.getHeaderPage().isDisplayed(), "Form is not closed");
        } catch (Exception e) {
            Assert.assertTrue(false, "Form is not closed");
        }
    }
}
