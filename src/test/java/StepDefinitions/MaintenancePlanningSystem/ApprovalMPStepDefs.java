package StepDefinitions.MaintenancePlanningSystem;

import StepDefinitions.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObjects.BaseObjectPage;
import pageObjects.MaintenancePlanningSystem.MP_MainTablePage;
import pageObjects.MaintenancePlanningSystem.MP_MaintenancePlanningFormPage;
import pageObjects.MaintenancePlanningSystem.MP_SearchFormPage;


public class ApprovalMPStepDefs extends BaseClass {
    public ApprovalMPStepDefs() {
        mpSearch = new MP_SearchFormPage();
        mpMainTable = new MP_MainTablePage();
        baseObjectPage = new BaseObjectPage();
        mpMaintenancePlanning = new MP_MaintenancePlanningFormPage();
    }

    @When("User click on submit approval button")
    public void user_click_on_submit_approval_button() {
        mpMaintenancePlanning.clickOnSubAprButton();
    }

    @When("User click on approval process field and choose as {string}")
    public void user_click_on_approval_process_field_and_choose_as(String approvalProcess) {
        baseObjectPage.clickAndChooseAprProcess(approvalProcess);
    }
    @Then("User should found approval process info in the table")
    public void user_should_found_approval_process_info_in_the_table() {
        boolean isDisplay = baseObjectPage.verifyDisplayArpProcessInfo();;
        Assert.assertTrue("Approval process info not display", isDisplay);
    }
    @When("User click on submit approval process button")
    public void user_click_on_submit_approval_process_button() {
        baseObjectPage.clickOnSubmitApprovalButton();
    }
    @Then("Close view form")
    public void close_view_form() {
        boolean isDisplay = baseObjectPage.verifyDisplayApprovalForm();;
        Assert.assertFalse("Approval form can't closed", isDisplay);
    }
    @Then("User should found maintenance planning in the table with status {string}")
    public void user_should_found_maintenance_planning_in_the_table_with_status(String status) {
        mpSearch.chooseStatus(status);
        boolean isContent = mpMainTable.verifyApprovalStatus(status);
        Assert.assertTrue("Not found maintenance planning", isContent);
    }

    //*****************************
    @When("User click on approval button")
    public void user_click_on_approval_button() {
        mpMaintenancePlanning.clickOnAprButton();
    }

    //**********************************
    @When("User click on return button")
    public void user_click_on_return_button() {
        mpMaintenancePlanning.clickOnReturnButton();
    }

    @When("User enter reason as {string}")
    public void user_enter_reason_as(String reason) {
        baseObjectPage.enterReason(reason);
    }
    @When("User click on return button in return form")
    public void user_click_on_return_button_in_return_form() {
        baseObjectPage.clickOnReturnButton();
    }

}
