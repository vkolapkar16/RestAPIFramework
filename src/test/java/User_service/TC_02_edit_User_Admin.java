package User_service;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base_Class.BaseClass_Admin;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class TC_02_edit_User_Admin extends BaseClass_Admin
{
	public String ENDPOINT="user/";
	FrameworkConfig cfg;
	
    @BeforeClass
	public void setup()
	{
		super.performLogin();
	}
	
    @Test
	public void editUserbyAdmin() throws IOException
	{
    	cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
		JSONObject request=new JSONObject();
		String token =TokenProvider.getInstance().getToken();
		request.put("first_name","Krishna");
		request.put("last_name","Kshrisagar");
		request.put("name","Krishna Kshrisagar");
		request.put("user_role","Admin");
		request.put("email","krishna.kshirsagar@xuriti.com");
		request.put("mobile_number","7447649691");
		request.put("status","Active");
		request.put("updatedAt",this.date());
		
	
 		 	String AdminID = this.AdminID();
 		 	String URL= ENDPOINT + AdminID;
			   
			String jsonString = request.toJSONString();
			 
		    	RequestSpecification httpRequest = RestAssured.given();
		   		httpRequest.contentType(ContentType.JSON);
		   		httpRequest.body(jsonString);
		   		httpRequest.header("authorization", "Bearer " + token);
		   		Response response = httpRequest.put(cfg.url() + URL);
		   		response.then().log().all().statusCode(200);
		   		
		   		JsonPath jsonPathEvaluator = response.jsonPath();
		   		boolean status = jsonPathEvaluator.get("status");
		   		String message = jsonPathEvaluator.get("message");
		   		System.out.println("Status received from Response: " + status);
		   		 
		   		 SoftAssert sa = new SoftAssert();
		   		// Assert.assertEquals(status, true, "Login not successful");
		   		// sa.assertNotEquals(message, "Staff already registered", "Correct message received in the Response");
		   		 sa.assertAll();
	}

}
