package StepDefinitions.webServices;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;

import Utils.Config;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import pageObject.webservices_entity.Reward;


public class MemberRewardServiceSteps {

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	HashMap<String, Reward> value ;


	private String ENDPOINT_GET_MEMBER_REWARDS = Config.getInstance().getRewardsEndpointURL();

	@Given("^A Member exists in Member Connect Application with unique \"([^\"]*)\"$")
	public void a_Member_exists_in_Member_Connect_Application_with_unique(String memberId) throws Throwable {
		request = given().param("q", "members" + memberId);
		System.out.println(request +":request");
	}

	@When("^User calls the Member rewards webservice to retreive with Member Id$")
	public void user_calls_the_Member_rewards_webservice_to_retreive_with_Member_Id() throws Throwable {
		response = request.relaxedHTTPSValidation().when().get(ENDPOINT_GET_MEMBER_REWARDS);
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("the status code is (\\d+)")
	public void verifyResponseStatus(int statusCode){
		json = response.then().statusCode(statusCode);
	}

	@Then("^user Verifies reward summary as below$")
	public void user_Verifies_reward_summary_as_below(List<Reward> sum) throws Throwable {
		try {
			value = new HashMap<String , Reward>();
			ResponseBody body = response.body();

			for(Reward value : sum) {
				if(body.asString().contains(value.getPoints()) &&
						body.asString().contains(value.getProviderAddress()) &&
						body.asString().contains(value.getProviderName()) &&
						body.asString().contains(value.getRewardDate())&& 
						body.asString().contains(value.getTreatment())) 
				{
					System.out.println("Pass: Response Content validated");
				}
			}}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Error: Response Content validation Failed");
			}
	}

}




