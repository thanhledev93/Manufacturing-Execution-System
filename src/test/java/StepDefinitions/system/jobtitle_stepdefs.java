package StepDefinitions.system;

import StepDefinitions.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.Auth.LoginPage;
import pageObjects.BaseObjectPage;
import pageObjects.sys.job_title.JobTitle_CreateFormPage;
import pageObjects.sys.job_title.JobTitle_MainTablePage;
import pageObjects.sys.job_title.JobTitle_SearchFormPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;


public class jobtitle_stepdefs extends BaseClass {
    public jobtitle_stepdefs() {
        baseObjectPage = new BaseObjectPage();
        jobt_create = new JobTitle_CreateFormPage();
        jobt_mainTable = new JobTitle_MainTablePage();
        jobt_search = new JobTitle_SearchFormPage();

    }

    @When("User click on create job title button")
    public void user_click_on_create_job_title_button() {
        jobt_search.clickOnCreateButton();
    }

    @Then("Open job title form")
    public void open_job_title_form() {
        Assert.assertTrue(jobt_create.verifyOpenCreateForm().equalsIgnoreCase("Chức danh"));
    }

    @When("User enter job title name {string} and note {string}")
    public void user_enter_job_title_name_and_note(String name, String note) {
        jobt_create.enterName(name);
        jobt_create.enterNote(note);
    }

    @When("User click on save button in job title form")
    public void user_click_on_save_button_in_job_title_form() {
        jobt_create.clickOnSaveButton();
    }

    @Then("User should found job title with {string} and {string} in the list")
    public void user_should_found_job_title_with_and_in_the_list(String name, String note) throws InterruptedException {
        boolean isSuccess = jobt_mainTable.verifyJobTitleIsDisplayedInTheList(name, note);
        Assert.assertTrue(isSuccess, "Create failed");
    }

    @Then("Display alert message {string} for required values in job title form")
    public void display_alert_message_for_required_values_in_job_title_form(String mes) {
        boolean isMes = jobt_create.verifyNameEquired(mes);
        Assert.assertTrue(isMes, "Can't display message");
    }

    @When("User enter job title note {string}")
    public void user_enter_job_title_note(String note) {
        jobt_create.enterNote(note);
    }

    @When("User click on close button in job title form")
    public void user_click_on_close_button_in_job_title_form() {
        jobt_create.clickOnCloseButton();
    }

    @Then("Close job title form")
    public void close_job_title_form() {
        Assert.assertNull(jobt_create.verifyOpenCreateForm(), "Can't close job title create form");
    }

    @When("User should not found job title with {string} and {string} in the list")
    public void user_should_not_found_job_title_with_and_in_the_list(String name, String note) throws InterruptedException {
        boolean isSuccess = jobt_mainTable.verifyJobTitleIsDisplayedInTheList(name, note);
        Assert.assertFalse(isSuccess, "Found job title");
    }

    // FEATURE: READ JOB TITLE ***********************************************************************************

    @When("User choose job title {string} and choose view")
    public void user_choose_job_title_and_choose_view(String name) {
        jobt_search.enterJobTitleName(name);
        jobt_search.clickOnSearchButton();
        jobt_mainTable.clickOnFeatureButtonInFirstRow();
        jobt_mainTable.clickOnViewButton();

    }

    @Then("Job title form fields are read-only")
    public void job_title_form_fields_are_read_only() {
        Assert.assertTrue(jobt_create.verifyReadOnlyFields(), "Field is not attribute read only");
    }

    @Then("Job title form fields {string} and {string} are loaded by default")
    public void job_title_form_fields_and_are_loaded_by_default(String name, String note) {
        Assert.assertTrue(jobt_create.verifyLoadValue(name, note), "Default value is not loaded");
    }


    // FEATURE: DELETE JOB TITLE
    @When("When user choose job title {string} and choose delete")
    public void when_user_choose_job_title_and_choose_delete(String name) {
        jobt_search.enterJobTitleName(name);
        jobt_search.clickOnSearchButton();
        jobt_mainTable.clickOnFeatureButtonInFirstRow();
        jobt_mainTable.clickOnDeleteButton();
    }

    //  FEATURE: REVERT JOB TITLE
    @Given("User has an unused job title")
    public void user_has_an_unused_job_title() {
        jobt_search.chooseStatus("Không sử dụng");
    }
    @When("User choose job title {string} and choose revert")
    public void user_choose_job_title_and_choose_revert(String name) {
        jobt_search.enterJobTitleName(name);
        jobt_search.clickOnSearchButton();
        jobt_mainTable.clickOnFeatureButtonInFirstRow();
        jobt_mainTable.clickOnRevertButton();
    }
    @When("User choose use status {string} for job title")
    public void user_choose_use_status_for_job_title(String status) {
        jobt_search.chooseStatus(status);
    }

    // FEATURE: SEARCH JOB TITLE **********************************************************************************************

    @When("User search a job title with {string} and {string}")
    public void user_search_a_job_title_with_and(String name, String status) {
        jobt_search.enterJobTitleName(name);
        jobt_search.chooseStatus(status);
        jobt_search.clickOnSearchButton();
    }
}
