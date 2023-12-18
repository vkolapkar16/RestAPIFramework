package User_service;

import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base_Class.BaseClass_Admin;
import Base_Class.TokenProvider;
import Utility.FrameworkConfig;
import Utility.MongoDB;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import java.io.IOException;

public class TC_01_get_User_Details extends BaseClass_Admin
{
	public String ENDPOINT="user/userlist";
	FrameworkConfig cfg;
    @BeforeClass
	public void setup()
	{
		super.performLogin();
	}
	
    @Test
	public void getUserDetails() throws IOException
	{
    	cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
		JSONObject request=new JSONObject();
		String token =TokenProvider.getInstance().getToken();

    	RequestSpecification httpRequest = RestAssured.given();
   		httpRequest.contentType(ContentType.JSON);
   		httpRequest.header("authorization", "Bearer " + token);
   		Response response = httpRequest.get(cfg.url() + ENDPOINT);
   		response.then().statusCode(200);
   		
   		JsonPath jsonPathEvaluator = response.jsonPath();
   		boolean status = jsonPathEvaluator.get("status");
   		String message = jsonPathEvaluator.get("message");
		String userNumber = jsonPathEvaluator.get("user.mobile_number");
   		
   		System.out.println("Status received from Response: " + status);
   		 
   		 SoftAssert sa = new SoftAssert();
   		 Assert.assertEquals(status, true, "Status Found :  False");
   		 sa.assertNotEquals(message, "Staff already registered", "Correct message received in the Response");
   		 sa.assertAll();
   		 
	 	}

}
