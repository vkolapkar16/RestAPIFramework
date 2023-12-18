package Auth_Services;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base_Class.BaseClass_User;
import Base_Class.TokenProvider;
import Utility.FrameworkConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_4_Send_OTP extends BaseClass_User
{
	FrameworkConfig cfg;

	 @BeforeClass
		public void setup()
		{
			super.performLogin();
		}
	 @Test
	public void sendOtp()
	{
		 cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
		  JSONObject request=new JSONObject();
		  String token = TokenProvider.getInstance().getToken();

		   request.put("recipient","8080630830");

			RequestSpecification httpRequest = RestAssured.given();
			httpRequest.contentType(ContentType.JSON);
			httpRequest.body(request.toJSONString());

			Response response = httpRequest.post(cfg.url() + "auth/send-otp");
			response.then().log().all().statusCode(400);

			JsonPath jsonPathEvaluator = response.jsonPath();
			boolean status = jsonPathEvaluator.get("status");
			String message = jsonPathEvaluator.get("message");

			System.out.println("Message received from Response: " + status);
			System.out.println("Message received from Response: " + message);

			Assert.assertEquals(status, false, "Correct status received in the Response");
			Assert.assertEquals(message, "Mobile number already registered.", "Correct status received in the Response");

		}


}
