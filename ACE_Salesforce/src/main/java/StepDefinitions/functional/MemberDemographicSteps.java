package StepDefinitions.functional;

import java.util.Map;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertArrayEquals;

import org.apache.commons.lang3.StringUtils;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import library.StepsLibrary;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class MemberDemographicSteps {

	StepsLibrary library = new StepsLibrary();

	/*@Given("^User logins in to member connect Application with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_logins_in_to_member_connect_Application_with_and(String userName , String password) throws Throwable {
		library.login(userName, password);

	}

	@Then("^User verifies Member demographics below member demographics$")
	public void user_verifies_Member_demographics_below_member_demographics(DataTable value) throws Throwable {
		List<Map<String, String>> dataMap = value.asMaps(String.class, String.class);
		library.verifyMemeberDemographicDetails(dataMap);
	}*/


	/*@When("^User clicks on Manager Alert tab and creates an Alert with \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_clicks_on_Manager_Alert_tab_and_creates_an_Alert_with_and_and_and_and(String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		library.clickManagerAlerttab(arg1, arg2, arg3, arg4, arg5);
	}

	@Then("^User verifies that an Alert is created successfully \"([^\"]*)\"$")
	public void user_verifies_that_an_Alert_is_created_successfully(String name) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    library.alertcreated(name);
	    }


	 @When("^User navigates to Created alert section and deletes an Alert$")
	 public void user_navigates_to_Created_alert_section_and_deletes_an_Alert() throws Throwable {
	     // Write code here that turns the phrase above into concrete actions
	     library.navigate_created_alert();
	 }

	 @Then("^User verifies that an Alert is deletd$")
	 public void user_verifies_that_an_Alert_is_deletd() throws Throwable {
	     // Write code here that turns the phrase above into concrete actions
	     library.deleted_alert();
	 }

	 @When("^User clicks on an alert and changes the alert status to \"([^\"]*)\"$")
	 public void user_clicks_on_an_alert_and_changes_the_alert_status_to(String arg1) throws Throwable {
	     // Write code here that turns the phrase above into concrete actions
	     library.changealertstatus(arg1);
	 }

	 @Then("^User verifies that the alert status has changed to \"([^\"]*)\"$")
	 public void user_verifies_that_the_alert_status_has_changed_to(String arg1) throws Throwable {
	     // Write code here that turns the phrase above into concrete actions
	    library.verifyalertstatus(arg1);
	 }
	 */

	/*@When("^User clicks on an alert and verifies alert tab details")
	public void user_clicks_on_an_alert_and_verifies_alert_tab_details() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		library.verifyAlertTableDetails();
	}

	@Then("^User verifies if the current date is in between start date and end date")
	public void user_verifies_if_current_date_is_in_between_start_date_and_end_date() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		library.verifySystemDate();
	}*/

	/* @When("^User navigates to Created alert section and deletes an Alert")
	 public void user_navigates_to_Created_alert_section_and_deletes_an_Alert() throws Throwable {
	     // Write code here that turns the phrase above into concrete actions
	     library.navigate_created_alert();
	 }
']
	 @Then("^User verifies that an Alert is deletd")
	 public void user_verifies_that_an_Alert_is_deletd() throws Throwable {
	     // Write code here that turns the phrase above into concrete actions
	     library.deleted_alert();
	 }*/


		Response response;
		private ValidatableResponse json;
		private RequestSpecification request;
		private String ENDPOINT_GET_BOOK_BY_ISBN = "https://postman-echo.com/get?test=123";

		@Given("a book exists with an isbn of (.*)")
		public void a_book_exists_with_isbn(String isbn){
			given();
		}

		@When("a user retrieves the book by isbn")
		public void a_user_retrieves_the_book_by_isbn(){
			response = request.get(ENDPOINT_GET_BOOK_BY_ISBN);
			
		}

		@Then("the status code is (\\d+)")
		public void verify_status_code(int statusCode){
			json = response.then().statusCode(statusCode);
			//assert.
			
	}
		
		/**
		 * asserts on json fields with single values
		 *//*
		@And("response includes the following$")
		public void response_equals(Map<String,String> responseFields){
			for (Map.Entry<String, String> field : responseFields.entrySet()) {
				if(StringUtils.isNumeric(field.getValue())){
					json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
				}
				else{
					json.body(field.getKey(), equalTo(field.getValue()));
				}
			}
		}
		
		
		*//**
		 * asserts on json arrays
		 *//*
		@And("response includes the following in any order")
		public void response_contains_in_any_order(Map<String,String> responseFields){
			for (Map.Entry<String, String> field : responseFields.entrySet()) {
				if(StringUtils.isNumeric(field.getValue())){
					json.body(field.getKey(), containsInAnyOrder(Integer.parseInt(field.getValue())));
				}
				else{
					json.body(field.getKey(), containsInAnyOrder(field.getValue()));
				}
			}
		}*/
		
}


