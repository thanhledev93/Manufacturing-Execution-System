package StepDefinitions.business_sale_order;


import StepDefinitions.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.Auth.LoginPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;


public class search_SO_StepDefs extends BaseClass {
    public search_SO_StepDefs() {

    }

//    Scenario: SO should appear in the SO list when searching with valid value
    @When("User search a SO with")
    public void user_search_a_so_with(io.cucumber.datatable.DataTable data) {
        List<List<String>> dTbl = data.asLists();
        boolean isLoad = mpMaintenancePlanning.showDefaultValues(dTbl.get(0));
        boolean tblDataLoaded = mpMaintenancePlanning.getTblRows() == 4;
        Assert.assertTrue(isLoad && tblDataLoaded, "Value of fields are not loaded by default");
    }
    @Then("SO should appear in the SO list")
    public void so_should_appear_in_the_so_list() {

    }
}
