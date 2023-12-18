package Auth_Services;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base_Class.BaseClass_Admin;
import Utility.FrameworkConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_1_Register_User extends BaseClass_Admin
{
	FrameworkConfig cfg;
	

	@Test
	public void Signup()
	{
	  cfg = ConfigFactory.create(FrameworkConfig.class);
	  JSONObject request=new JSONObject();


	   request.put("firstName","Pranjal");
	   request.put("lastName","Gawade");
	   request.put("mobileNumber","9763926680");
	   request.put("email","varsha.pati@tech-trail.com");
	   request.put("password","Varsha12#");

	   RequestSpecification httpRequest = RestAssured.given();
		httpRequest.contentType(ContentType.JSON);
		httpRequest.body(request.toJSONString());

		Response response = httpRequest.post(cfg.url() + "auth/register-user");
		response.then().log().all().statusCode(200);

		JsonPath jsonPathEvaluator = response.jsonPath();
		boolean status = jsonPathEvaluator.get("status");
		String message = jsonPathEvaluator.get("message");

		System.out.println("Message received from Response: " + status);
		System.out.println("Message received from Response: " + message);

		Assert.assertEquals(status, false, "Correct status received in the Response");
		Assert.assertEquals(message, "Please verify your mobile number first using OTP", "Correct status received in the Response");

	}
}
