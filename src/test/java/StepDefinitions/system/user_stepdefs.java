package StepDefinitions.system;

import StepDefinitions.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.BaseObjectPage;
import pageObjects.sys.user.User_CreateFormPage;
import pageObjects.sys.user.User_MainTablePage;
import pageObjects.sys.user.User_SearchFormPage;

import java.util.List;


public class user_stepdefs extends BaseClass {
    public user_stepdefs() {
        baseObjectPage = new BaseObjectPage();
        user_create = new User_CreateFormPage();
        user_mainTable = new User_MainTablePage();
        user_search = new User_SearchFormPage();
    }
    @When("User click on create user button")
    public void user_click_on_create_user_button() {
        user_search.clickOnCreateButton();
    }
    @Then("Open user form")
    public void open_user_form() {
        Assert.assertTrue(user_create.verifyOpenCreateForm().equalsIgnoreCase("Người dùng"));
    }
    @When("User enter firstname {string} and lastname {string}")
    public void user_enter_firstname_and_lastname(String firstname, String lastname) {
        user_create.enterFirstName(firstname);
        user_create.enterLastName(lastname);
    }
    @When("User choose department {string} and job title {string}")
    public void user_choose_department_and_job_title(String dept, String jobt) {
        user_create.chooseDept(dept);
        user_create.chooseJobt(jobt);
    }
    @When("User enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {
        user_create.enterUsername(username);
        user_create.enterPassword(password);
    }
    @When("User click on save button in user form")
    public void user_click_on_save_button_in_user_form() {
        user_create.clickOnSaveButton();
    }
    @Then("User should found user with {string} {string} {string} {string} {string} in the list")
    public void user_should_found_user_with_in_the_list(String firstname, String lastname, String dept, String user, String username) throws InterruptedException {
        boolean isSuccess = user_mainTable.verifyUserIsDisplayedInTheList(firstname, lastname, dept, user, username);
        Assert.assertTrue(isSuccess, "Create failed");
    }
    @Then("Display alert message {string} for required values in user form")
    public void display_alert_message_for_required_values_in_user_form(String mes) {
        boolean isMes = user_create.verifyEquiredValues(mes);
        Assert.assertTrue(isMes, "Can't display message");
    }
    @Then("Display alert message {string} for username in user form")
    public void display_alert_message_for_username_in_user_form(String mes) {
        boolean isMes = user_create.verifyAlreadyExistUsername(mes);
        Assert.assertTrue(isMes, "Can't display message");
    }

    @When("User click on close button in user form")
    public void user_click_on_close_button_in_user_form() {
        user_create.clickOnCloseButton();
    }
    @Then("Close user form")
    public void close_user_form() {
        Assert.assertNull(user_create.verifyOpenCreateForm(), "Can't close user create form");
    }

    // READ
    @When("User choose username as {string} and choose view")
    public void user_choose_username_as_and_choose_view(String username) {
        user_search.enterUserName(username);
        user_search.clickOnSearchButton();
        user_mainTable.clickOnFeatureButtonInFirstRow();
        user_mainTable.clickOnViewButton();
    }
    @Then("User form fields are read-only")
    public void user_form_fields_are_read_only() {
        Assert.assertTrue(user_create.verifyReadOnlyFields(), "Field is not attribute read only");
    }
    @Then("User form fields are loaded by default")
    public void user_form_fields_are_loaded_by_default(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> dTbl = dataTable.asLists();
        boolean isLoad = user_create.showDefaultValuesForRead(dTbl.get(1));
        Assert.assertTrue(isLoad, "Value of fields are not loaded by default");
    }

    // DELETE

    @When("User choose username as {string} and choose delete")
    public void user_choose_username_as_and_choose_delete(String username) {
        user_search.enterUserName(username);
        user_search.clickOnSearchButton();
        user_mainTable.clickOnFeatureButtonInFirstRow();
        user_mainTable.clickOnDeleteButton();
    }
    @Then("User should not found user in the list")
    public void user_should_not_found_user_in_the_list(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<List<String>> userInfors = dataTable.asLists();
        boolean isDelete = user_mainTable.verifyUserIsDisplayedInTheList(userInfors.get(1));
        Assert.assertFalse(isDelete, "Found user");
    }
    @Then("User should found user in the list")
    public void user_should_found_user_in_the_list(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<List<String>> userInfors = dataTable.asLists();
        boolean isDelete = user_mainTable.verifyUserIsDisplayedInTheList(userInfors.get(1));
        Assert.assertTrue(isDelete, "Not found user");
    }

    // SEARCH
    @When("User search with lastname as {string}")
    public void user_search_with_lastname_as(String name) {
        user_search.enterUserName(name);
    }
    @When("User search with username as {string}")
    public void user_search_with_username_as(String name) {
        user_search.enterUserName(name);
    }

    //UPDATE
    @When("User choose username as {string} and choose update")
    public void user_choose_username_as_and_choose_update(String username) {
        user_search.enterUserName(username);
        user_search.clickOnSearchButton();
        user_mainTable.clickOnFeatureButtonInFirstRow();
        user_mainTable.clickOnEditButton();
    }
    @Then("User form fields are loaded by default for update")
    public void user_form_fields_are_loaded_by_default_for_update(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> dTbl = dataTable.asLists();
        boolean isLoad = user_create.showDefaultValuesForUpdate(dTbl.get(1));
        Assert.assertTrue(isLoad, "Value of fields are not loaded by default");
    }
    @When("User enter user info with")
    public void user_enter_user_info_with(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> userInfors = dataTable.asLists();
        user_create.enterUserInfor(userInfors.get(1));
    }

    @When("User clear username and password")
    public void user_clear_username_and_password() {
       user_create.clearUsername();
       user_create.clearPassword();
    }
    @When("User enter user as {string} and password as {string}")
    public void user_enter_user_as_and_password_as(String username, String password) {
        user_create.enterUsername(username);
        user_create.enterPassword(password);
    }
}
