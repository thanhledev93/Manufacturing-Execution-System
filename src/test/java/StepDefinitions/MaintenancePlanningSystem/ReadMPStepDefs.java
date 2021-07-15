package StepDefinitions.MaintenancePlanningSystem;

import StepDefinitions.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
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
            Assert.assertTrue("Not found maintenance planning", mpMainTable.getNoOfRows() > 0);
    }

    @When("User choose maintenance planning and click on feature button and choose view")
    public void user_choose_maintenance_planning_and_click_on_feature_button_and_choose_view() {
        mpMainTable.clickOnFeatureButtonInFirstRow();
        mpMainTable.clickOnViewButton();
    }
    @Then("Open maintenance planning form")
    public void open_maintenance_planning_form() {
        boolean isFormDisplay = mpMaintenancePlanning.verifyOpenMPForm();
        Assert.assertTrue("Maintenance planning form not opened", isFormDisplay);
    }
    @Then("Maintenance planning form fields are read-only")
    public void maintenance_planning_form_fields_are_read_only() {
        boolean isReadOnly = mpMaintenancePlanning.verifyReadOnly();
        Assert.assertTrue("There is a field that is not assigned read-only attribute", isReadOnly);
    }

    @Then("Maintenance planning form fields are loaded by default")
    public void maintenance_planning_form_fields_are_loaded_by_default(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> dTbl = dataTable.asLists();
        boolean isLoad = mpMaintenancePlanning.showDefaultValues(dTbl.get(0));
        boolean tblDataLoaded = mpMaintenancePlanning.getTblRows() == 4;
        Assert.assertTrue("Value of fields are not loaded by default", isLoad && tblDataLoaded);
    }
    @When("User click on detail button")
    public void user_click_on_detail_button() {
        mpMaintenancePlanning.clickOnDetailButton();
    }

    @Then("Open detail calendar form and form fields are loaded by default")
    public void open_detail_calendar_form_and_form_fields_are_loaded_by_default() {
       boolean tblDataLoaded = mpDetailCalendar.getTblRows() == 7;
       Assert.assertTrue("Detail calendar form fields are loaded by default", tblDataLoaded);
    }
    @When("User click on close button in detail calendar form")
    public void user_click_on_close_button_in_detail_calendar_form() {
       mpDetailCalendar.clickOnCloseButton();
    }

    @Then("Close detail calendar form")
    public void close_detail_calendar_form() throws InterruptedException {
        Thread.sleep(1000);

        boolean isCloseDCForm = mpDetailCalendar.verifyOpenMPForm();
        Assert.assertTrue("Detail calendar form is not closed", isCloseDCForm);
    }

    @When("User click on close button in maintenance planning form")
    public void user_click_on_close_button_in_maintenance_planning_form() {
        mpMaintenancePlanning.clickOnCloseButton();
    }
    @Then("Close maintenance planning form")
    public void close_maintenance_planning_form() {
        try {
            Assert.assertTrue("Form is not closed", baseObjectPage.getHeaderPage().isDisplayed());
        } catch (Exception e) {
            Assert.assertTrue("Form is not closed",false);
        }
    }
}
