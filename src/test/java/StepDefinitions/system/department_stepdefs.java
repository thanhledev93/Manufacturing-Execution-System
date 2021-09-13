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
import pageObjects.sys.department.Department_CreateFormPage;
import pageObjects.sys.department.Department_MainTablePage;
import pageObjects.sys.department.Department_SearchFormPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;


public class department_stepdefs extends BaseClass {
    public department_stepdefs() {
        loginPage = new LoginPage();
        baseObjectPage = new BaseObjectPage();
        dept_createForm = new Department_CreateFormPage();
        dept_mainTable = new Department_MainTablePage();
        dept_Search = new Department_SearchFormPage();
    }

    @Given("User is on {string} page with account {string} and password {string}")
    public void user_is_on_page_with_account_and_password(String page, String username, String password) {
        getDriver().get(baseUrl);
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        WebElement mnu = getDriver().findElement(By.xpath("//span[contains(text(),'"+ page + "')]//ancestor::a"));

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", mnu);
        clickOn(getDriver(), mnu);
    }

//    FEATURE: CREATE DEPARTMENT ***************************************************************************************

    @When("User click on create department button")
    public void user_click_on_create_department_button() {
        dept_Search.clickOnCreateButton();
    }
    @Then("Open department form")
    public void open_department_form() {
       Assert.assertEquals("Phòng ban", dept_createForm.verifyOpenCreateForm());
    }
    @When("User enter department name {string} and note {string}")
    public void user_enter_department_name_and_note(String name, String note) {
        dept_createForm.enterName(name);
        dept_createForm.enterNote(note);
    }
    @When("User click on save button in department form")
    public void user_click_on_save_button_in_department_form() {
        dept_createForm.clickOnSaveButton();
    }
    @Then("Display alert message {string}")
    public void display_alert_message(String message) {
        boolean isDisplay = baseObjectPage.verifyAlertMes(message);
        Assert.assertTrue(isDisplay, "The message is not displayed");
    }

    @When("User click on confirm button")
    public void user_click_on_confirm_button() {
        baseObjectPage.clickOnCloseAlertMes();
    }
    @Then("Close alert message")
    public void close_alert_message() {
        await().atMost(5000, TimeUnit.SECONDS).untilAsserted(()
                -> Assert.assertFalse(baseObjectPage.verifyCloseAlertMes(), "Can't closed popup"));
    }

    @Then("User should found department with {string} and {string} in the list")
    public void user_should_found_department_with_and_in_the_list(String name, String note) throws InterruptedException {
        boolean isSuccess = dept_mainTable.verifyDepartmentIsDisplayedInTheList(name, note);
        Assert.assertTrue(isSuccess, "Adding new departments failed");
    }

    @When("User enter note {string}")
    public void user_enter_note(String note) {
        dept_createForm.enterNote(note);
    }
    @Then("Display alert message {string} for required values in department form")
    public void display_alert_message_for_required_values_in_department_form(String mes) {
        boolean isMes = dept_createForm.verifyAlertMesForNameEquired(mes);
        Assert.assertTrue(isMes, "Can't display message");
    }

    @When("User enter department name and note")
    public void user_enter_department_name_and_note(io.cucumber.datatable.DataTable dataTable) {
        List<String> dTbl = dataTable.asLists().get(0);
        dept_createForm.enterName(dTbl.get(0));
        dept_createForm.enterNote(dTbl.get(1));
    }
    @When("User click on close button in department form")
    public void user_click_on_close_button_in_department_form() {
        dept_createForm.clickOnCloseButton();
    }
    @Then("Close department form")
    public void close_department_form() {
        Assert.assertNull(dept_createForm.verifyOpenCreateForm(), "Can't close department create form");
    }

    // FEATURE: READ DEPARTMENT ***********************************************************************************

    @When("User choose department {string} and choose view")
    public void user_choose_department_and_choose_view(String name) {
        dept_Search.enterDepartmentName(name);
        dept_Search.clickOnSearchButton();
        dept_mainTable.clickOnFeatureButtonInFirstRow();
        dept_mainTable.clickOnViewButton();

    }
    @Then("Department form fields are read-only")
    public void department_form_fields_are_read_only() {
        Assert.assertTrue(dept_createForm.verifyReadOnlyFields(), "Field is not attribute read only");
    }
    @Then("Department form fields {string} and {string} are loaded by default")
    public void department_form_fields_and_are_loaded_by_default(String name, String note) {
        Assert.assertTrue(dept_createForm.verifyLoadValue(name, note), "Default value is not loaded");
    }


    // FEATURE: SEARCH DEPARTMENT **********************************************************************************************

    @When("User search a department with {string} and {string}")
    public void user_search_a_department_with_and(String name, String status) {
        dept_Search.enterDepartmentName(name);
        dept_Search.chooseStatus(status);
        dept_Search.clickOnSearchButton();
    }
    @And("Close browser")
    public void close_browser() {
        getDriver().quit();
    }

    // FEATURE: UPDATE DEPARTMENT **************************************************************************************
    @When("User choose department {string} and choose update")
    public void user_choose_department_and_choose_update(String name) {
        dept_Search.enterDepartmentName(name);
        dept_Search.clickOnSearchButton();
        dept_mainTable.clickOnFeatureButtonInFirstRow();
        dept_mainTable.clickOnEditButton();
    }

    @When("User clear department name")
    public void user_clear_department_name() {
       dept_createForm.clearName();
    }
    @When("User should not found department with {string} and {string} in the list")
    public void user_should_not_found_department_with_and_in_the_list(String name, String note) throws InterruptedException {
        boolean isSuccess = dept_mainTable.verifyDepartmentIsDisplayedInTheList(name, note);
        Assert.assertFalse(isSuccess, "Found department");
    }

    // FEATURE: DELETE DEPARTMENT
    @When("When user choose department {string} and choose delete")
    public void when_user_choose_department_and_choose_delete(String name) {
        dept_Search.enterDepartmentName(name);
        dept_Search.clickOnSearchButton();
        dept_mainTable.clickOnFeatureButtonInFirstRow();
        dept_mainTable.clickOnDeleteButton();
    }


    @Then("Display alert message as {string}")
    public void display_alert_message_as(String mes) {
        boolean isDisplay = baseObjectPage.verifyAlertMes(mes);
        Assert.assertTrue(isDisplay, "The message is not displayed");
    }
    // t
    @When("User click on yes button")
    public void user_click_on_yes_button() {
        baseObjectPage.clickOnConfirmAlertMes();
    }
    @When("User click on no button")
    public void user_click_on_no_button() {
        baseObjectPage.clickOnCloseAlertMes();
    }

//  FEATURE: REVERT DEPARTMENT
    @Given("User has an unused department")
    public void user_has_an_unused_department() {
       dept_Search.chooseStatus("Không sử dụng");
    }
    @When("User choose department {string} and choose revert")
    public void user_choose_department_and_choose_revert(String name) {
        dept_Search.enterDepartmentName(name);
        dept_Search.clickOnSearchButton();
        dept_mainTable.clickOnFeatureButtonInFirstRow();
        dept_mainTable.clickOnRevertButton();
    }
    @When("User choose use status as {string}")
    public void user_choose_use_status_as(String status) {
        dept_Search.chooseStatus(status);
    }

}
