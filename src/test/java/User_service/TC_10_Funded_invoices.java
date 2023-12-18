package User_service;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base_Class.BaseClass_Admin;
import Base_Class.TokenProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertNotNull;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TC_10_Funded_invoices extends BaseClass_Admin
{
	public String ENDPOINT="user/invoicefunded/dashboard";
	
    @BeforeClass
	public void setup()
	{
		super.performLogin();
	}
	
    @Test
	public void getFundedInvoices()
	{
		JSONObject request=new JSONObject();
		String token =TokenProvider.getInstance().getToken();

    	RequestSpecification httpRequest = RestAssured.given();
   		httpRequest.contentType(ContentType.JSON);
   	//	httpRequest.body(jsonString);
   		httpRequest.header("authorization", "Bearer " + token);
   		Response response = httpRequest.get(CONTEXTPATH + ENDPOINT);
   		response.then().log().all();
   		
   		JsonPath jsonPathEvaluator = response.jsonPath();
   		boolean status = jsonPathEvaluator.get("status");
   		String message = jsonPathEvaluator.get("message");
		int invamount = jsonPathEvaluator.get("inv_amount");
   		System.out.println(invamount);
   		System.out.println("Status received from Response: " + status);
   		 
   		SoftAssert sa = new SoftAssert();
   		Assert.assertEquals(status, true, "Status Found :  False");		
   		assertNotNull(invamount);
	}

}
