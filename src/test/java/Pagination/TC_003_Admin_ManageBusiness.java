package Pagination;

import java.io.IOException;
import java.util.ArrayList;

import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base_Class.BaseClass_Admin;
import Base_Class.TokenProvider;
import Utility.FrameworkConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_003_Admin_ManageBusiness extends BaseClass_Admin
{
	FrameworkConfig cfg;
	   @BeforeClass
		public void setup()
		{
			super.performLogin();
		}
		
	   @Test
		public void getAllCompanies() throws IOException
		{
		   cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
		  JSONObject request=new JSONObject();
		  String token = TokenProvider.getInstance().getToken();
		  	  
		  RequestSpecification httpRequest = RestAssured.given();
			httpRequest.contentType(ContentType.JSON);
			httpRequest.body(request.toJSONString());
			httpRequest.header("authorization", "Bearer " + token);

			Response response = httpRequest.get(cfg.url() + "entity/entities");
			response.then().statusCode(200);

			JsonPath jsonPathEvaluator = response.jsonPath();
			boolean status = jsonPathEvaluator.get("status");
			ArrayList<Integer> TotalCompanies = jsonPathEvaluator.get("companies");
			int resultCount = jsonPathEvaluator.get("length");
			
			System.out.println("Total Companies= "+resultCount);
			System.out.println("Message received from Response: " + status);
			System.out.println("Total Companies Fetch in Response : " + TotalCompanies.size());
			
			Assert.assertEquals(status, true, "Correct status received in the Response"); 
			Assert.assertNotNull(TotalCompanies, "No invoices Found");
		}

}
