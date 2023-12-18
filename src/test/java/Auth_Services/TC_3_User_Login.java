package Auth_Services;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base_Class.BaseClass_User;
import Base_Class.TokenProvider;
import Utility.FrameworkConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_3_User_Login extends BaseClass_User
{
	FrameworkConfig cfg;
	public void setup() {
		super.performLogin();

}

      @Test(priority = 1,enabled = false)
	  public String Login()
{

    	  cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
		  JSONObject request=new JSONObject();

		   request.put("email","varsha.patil@tech-trail.com");
		   request.put("password","Xuriti#20");

		   RequestSpecification httpRequest = RestAssured.given();
			httpRequest.contentType(ContentType.JSON);
			httpRequest.body(request.toJSONString());

			Response response = httpRequest.post(cfg.url() + "auth/login");
			response.then().log().all().statusCode(200);

			JsonPath jsonPathEvaluator = response.jsonPath();
			boolean status = jsonPathEvaluator.get("status");
			String message = jsonPathEvaluator.get("message");
			String userID=jsonPathEvaluator.get("user._id");

			System.out.println("Message received from Response: " + status);
			System.out.println("Message received from Response: " + message);
			System.out.println("User ID After Login " + userID);

			Assert.assertEquals(status, true, "Correct status received in the Response");
			Assert.assertEquals(message, "Welcome Varsha Patilll", "Correct status received in the Response");

			return userID;
	}

     @Test(priority=2,enabled = false)
	  public void Logout()
{
	 cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
	  JSONObject request=new JSONObject();

	  String token = TokenProvider.getInstance().getToken();
	   request.put("userID",Login());

	   RequestSpecification httpRequest = RestAssured.given();
		httpRequest.contentType(ContentType.JSON);
		httpRequest.body(request.toJSONString());
		httpRequest.header("authorization", "Bearer " + token);

		Response response = httpRequest.post(cfg.url() + "auth/logout");
		response.then().log().all().statusCode(200);

		JsonPath jsonPathEvaluator = response.jsonPath();
		boolean status = jsonPathEvaluator.get("status");
		String message = jsonPathEvaluator.get("message");

		System.out.println("Message received from Response: " + status);
		System.out.println("Message received from Response: " + message);

		Assert.assertEquals(status, true, "Correct status received in the Response");
		Assert.assertEquals(message, "Logged Out", "Correct status received in the Response");
	}
 }


