package Base_Class;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;

import Utility.FrameworkConfig;
import Utility.Mongoutil;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class BaseClass_User extends Mongoutil

{
	  FrameworkConfig cfg;
	  

	private static Properties properties;
	
	   public void performLogin() {
		   if(!TokenProvider.getInstance().hasToken())
		   {
			   
			   cfg = ConfigFactory.create(FrameworkConfig.class);
	
			   JSONObject request=new JSONObject();
			   
			   request.put("email",cfg.username());
			   request.put("password",cfg.password());
			   
			     ValidatableResponse response = given().
				   contentType("application/json").
			      body(request.toJSONString()).
			   when().
			      post(cfg.url()+ "auth/login").
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
