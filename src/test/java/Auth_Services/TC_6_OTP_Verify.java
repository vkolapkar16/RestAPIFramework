package Auth_Services;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base_Class.BaseClass_User;
import Base_Class.ConfigReader;
import Utility.FrameworkConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_6_OTP_Verify extends BaseClass_User
{
	FrameworkConfig cfg;
	@DataProvider(name="OTP_verify")
	public Object [][] verify_OTP()
	{
      Object [][] data= new Object[6][2];

		data[0][0]="7788";
		data[0][1]="9763926686";

		data[1][0]="";
		data[1][1]="9763926686";

		data[2][0]="732788";
		data[2][1]="9763926686";

		data[3][0]="AS@1";
		data[3][1]="9763926686";

		data[4][0]="7788";
		data[4][1]="";

		data[5][0]="";
		data[5][1]="";

		return data;

	}

	@Test(dataProvider = "OTP_verify")
	public void Verify_Otp(String mobileNumber, String OTP)
	{
		 cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
		JSONObject request=new JSONObject();
		String CONTEXTPATH = ConfigReader.getProperty("CONTEXTPATH");

		   request.put("mobileNumber",mobileNumber);
		   request.put("otp", OTP);

		   RequestSpecification httpRequest = RestAssured.given();
			httpRequest.contentType(ContentType.JSON);
			httpRequest.body(request.toJSONString());

			Response response = httpRequest.post(cfg.url() + "auth/verify-otp");
			response.then().log().all().statusCode(200);

}
}
