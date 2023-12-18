package Base_Class;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;

import Utility.FrameworkConfig;
import Utility.Mongoutil;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class BaseClass_Admin extends Mongoutil
{
	public static final String CONTEXTPATH="http://13.232.181.249/";
	//public static final String CONTEXTPATH="https://dev.xuriti.app/api/";
	//public static final String CONTEXTPATH="http://192.168.0.128/";
	public static final String buyerid="6425365e11f6443ab8b137b2";
	public static final String userid="64083a043810423000115818";
	  FrameworkConfig cfg;
	
	
	 
	   public void performLogin() {
		   if(!TokenProvider.getInstance().hasToken())
		   {
			   cfg = ConfigFactory.create(FrameworkConfig.class);
			   JSONObject request=new JSONObject();
//			   
			   request.put("email",cfg.adminmail());
	           request.put("password", cfg.adminpwd());
			   
//		     System.out.println(request.toJSONString());
	 
			     ValidatableResponse response = given().
				   contentType("application/json").
			      body(request.toJSONString()).
			   when().
			      post(cfg.url() + "auth/login").
			  // then().log().all().
			      then();
		     	response.statusCode(200);
		     	 
			   String token = accessToken(response.extract().response());
			   TokenProvider.getInstance().setToken(token);
			   
			   
			   
		   }
	   }

	public String accessToken (Response response) 
	{
		
		return response.jsonPath().getString("token");
	}
}
