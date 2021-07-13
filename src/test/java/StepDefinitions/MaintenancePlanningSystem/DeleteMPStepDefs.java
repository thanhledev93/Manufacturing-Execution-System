package StepDefinitions.MaintenancePlanningSystem;

import StepDefinitions.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObjects.BaseObjectPage;
import pageObjects.MaintenancePlanningSystem.MP_MainTablePage;
import pageObjects.MaintenancePlanningSystem.MP_SearchFormPage;


public class DeleteMPStepDefs extends BaseClass {
    public DeleteMPStepDefs() {
        mpSearch = new MP_SearchFormPage();
        mpMainTable = new MP_MainTablePage();
        baseObjectPage = new BaseObjectPage();
    }
    @Given("There is a maintenance plan with status as {string}")
    public void there_is_a_maintenance_plan_with_status_as(String status) throws InterruptedException {

        mpSearch.chooseFact("Sản xuất");
        Thread.sleep(1000);

        mpSearch.chooseFactLine("Cắt phôi");
        Thread.sleep(1000);

        mpSearch.chooseStatus(status);
        Thread.sleep(1000);

        Assert.assertTrue("Not found maintenance planning", mpMainTable.getNoOfRows() > 0);

    }

    @When("User choose maintenance planning and click on feature button and choose delete")
    public void user_choose_maintenance_planning_and_click_on_feature_button_and_choose_delete() {
        mpMainTable.clickOnFeatureButtonInFirstRow();
        mpMainTable.clickOnDeleteButton();
    }
    @Then("User should not found maintenance planning just deleted in the table")
    public void user_should_not_found_maintenance_planning_just_deleted_in_the_table() throws InterruptedException {
        int isContent = mpMainTable.getRowIndex();
        Assert.assertTrue("Found maintenance planning", isContent == -1);
    }

    //****************************
    @When("User click on no button")
    public void user_click_on_no_button() {
        baseObjectPage.clickOnCloseAlertMes();
    }

    @When("User should found maintenance planning just deleted in the table")
    public void user_should_found_maintenance_planning_just_deleted_in_the_table() {
        int isContent = mpMainTable.getRowIndex();
        Assert.assertTrue("Not found maintenance planning", isContent != -1);
    }


}
