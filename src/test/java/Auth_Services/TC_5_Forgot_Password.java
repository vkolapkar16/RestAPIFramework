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

public class TC_5_Forgot_Password extends BaseClass_User
{
	FrameworkConfig cfg;
	@DataProvider(name="forgot_pass")
	public Object [][] forgot_password()
	{
      Object [][] data= new Object[4][1];

		data[0][0]="varsha.patil@tech-trail.com";

		data[1][0]="varshap";

		data[2][0]="varshapgmail.com";

		data[3][0]="&&&&&";

		return data;

	}

	@Test(dataProvider = "forgot_pass")
	public void Forgot_Password(String Email)
	{
		cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
		JSONObject request=new JSONObject();
		String CONTEXTPATH = ConfigReader.getProperty("CONTEXTPATH");

		   request.put("email",Email);

		   RequestSpecification httpRequest = RestAssured.given();
			httpRequest.contentType(ContentType.JSON);
			httpRequest.body(request.toJSONString());

			Response response = httpRequest.post(cfg.url() + "auth/forgot-password");
			response.then().log().all().statusCode(200);

	}

}
