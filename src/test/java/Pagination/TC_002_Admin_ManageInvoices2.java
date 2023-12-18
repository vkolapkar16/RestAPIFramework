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

public class TC_002_Admin_ManageInvoices2 extends BaseClass_Admin
{
	FrameworkConfig cfg;
	   @BeforeClass
		public void setup()
		{
			super.performLogin();
		}
		
	   @Test
		public void getAllInvoices() throws IOException
		{
		   cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
		  JSONObject request=new JSONObject();
		  String token = TokenProvider.getInstance().getToken();
		  
		   int Upperlimit = 99;

		  
		  RequestSpecification httpRequest = RestAssured.given();
			httpRequest.contentType(ContentType.JSON);
			httpRequest.queryParam("lowerLimit", "1");
			httpRequest.queryParam("upperLimit", Upperlimit);
			httpRequest.body(request.toJSONString());
			httpRequest.header("authorization", "Bearer " + token);

			Response response = httpRequest.get(cfg.url() + "invoice/invoices");
			response.then().statusCode(200);

			JsonPath jsonPathEvaluator = response.jsonPath();
			boolean status = jsonPathEvaluator.get("status");
			ArrayList<Integer> TotalInvoices = jsonPathEvaluator.get("invoice");
			int totalInvoices = TotalInvoices.size();
			int resultCount = jsonPathEvaluator.get("resultCount");
			
			System.out.println("Invoices Count = "+resultCount);
			System.out.println("Message received from Response: " + status);
			System.out.println("Total Invoices Fetched = "+totalInvoices);
			
			Assert.assertEquals(status, true, "Correct status received in the Response"); 
			Assert.assertNotNull(TotalInvoices, "No invoices Found");
			Assert.assertEquals(Upperlimit, totalInvoices);
		}

}
