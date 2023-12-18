package User_service;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base_Class.BaseClass_Admin;
import Base_Class.BaseClass_User;
import Base_Class.ConfigReader;
import Base_Class.TokenProvider;
import Utility.FrameworkConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TC_11_get_user extends BaseClass_User
{
	public String ENDPOINT="user/getuser/";
	public String ENDPOINT1="/company/";
	FrameworkConfig cfg;
	
    @BeforeClass
	public void setup()
	{
		super.performLogin();
	}
	
    @Test(enabled = false)
	public void getUserList() throws IOException
	{
    	cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
		JSONObject request=new JSONObject();
		String token =TokenProvider.getInstance().getToken();
		
		String UserID = this.UserPanelID();
		String CompanyID = this.Company_UserID();
		
		String URL=ENDPOINT + UserID + ENDPOINT1 + CompanyID;
		
		RequestSpecification httpRequest = RestAssured.given();
   		httpRequest.contentType(ContentType.JSON);
   		httpRequest.header("authorization", "Bearer " + token);
   		Response response = httpRequest.get(cfg.url() + URL);
   		response.then().statusCode(200);
   		
   		JsonPath jsonPathEvaluator = response.jsonPath();
   		boolean status = jsonPathEvaluator.get("status");
   		String message = jsonPathEvaluator.get("message");
   		
   		System.out.println("Status received from Response: " + status);
   		 
   		SoftAssert sa = new SoftAssert();
   		Assert.assertEquals(status, true, "Status Found :  False");
   		sa.assertNotEquals(message, "Staff already registered", "Correct message received in the Response");
   		sa.assertAll();
	 	}

}
