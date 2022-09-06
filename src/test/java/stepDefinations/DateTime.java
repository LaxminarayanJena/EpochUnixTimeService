package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
import org.hamcrest.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.Utils;

public class DateTime extends Utils {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;

	@Given("user calls {string} with {string} http request with unixtimeStamp as {string}")
	public void user_calls_with_http_request_with_unixtime_stamp_as(String resource, String method,
			String unixtimestamp) throws IOException {
		res = given().spec(requestSpecification()).queryParam("unixtimestamp", unixtimestamp);

		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST")) {
			response = res.when().post(resourceAPI.getResource());
		} else if (method.equalsIgnoreCase("GET")) {
			response = res.when().get(resourceAPI.getResource());
		}
	}

	@Then("the API call is sucess with status code {int}")
	public void the_api_call_is_sucess_with_status_code(Integer statusCode) {
		assertEquals(response.getStatusCode(), 200);

	}

	@Then("the API call is sucess with errormsg and status code {int}")
	public void the_api_call_is_sucess_with_errormsg_and_statusCode(Integer statusCode) {
		assertEquals(response.getStatusCode(), 400);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {

		String Datetime = getJsonPath(response, "Datetime");
		System.out.println(Datetime);
		assertNotNull(Datetime);

	}

	@Then("errorMsg in response body is {string}")
	public void error_msg_in_response_body_is(String errorMsg) {
		String Error = getJsonPath(response, "Error");
		System.out.println(Error);
		assertNotNull(Error);
		assertEquals(Error, errorMsg);

		
	}

}
