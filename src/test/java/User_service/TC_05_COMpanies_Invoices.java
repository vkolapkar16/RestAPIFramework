package User_service;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base_Class.BaseClass_Admin;
import Base_Class.TokenProvider;
import Utility.FrameworkConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TC_05_COMpanies_Invoices extends BaseClass_Admin
{
	public String ENDPOINT="user/invoices/dashboard";
	FrameworkConfig cfg;
    @BeforeClass
	public void setup()
	{
		super.performLogin();
	}
	
    @Test
	public void getCompaniesInvoices()
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
   		String id = jsonPathEvaluator.get("invoicelist[1]._id");
   		String buyergst = jsonPathEvaluator.get("invoicelist[1].buyer_gst");
   		String sellergst = jsonPathEvaluator.get("invoicelist[1].seller_gst");
   		
   		System.out.println("Status received from Response: " + status);
   		 
   		 SoftAssert sa = new SoftAssert();
   		 Assert.assertEquals(status, true, "Data Fetched Successfully");
   		 sa.assertNotNull(id);
   	 	 sa.assertNotNull(buyergst);
   		 sa.assertNotNull(sellergst);
   		 sa.assertAll();
	}

}
