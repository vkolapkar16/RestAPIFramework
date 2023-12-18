package Auth_Services;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base_Class.BaseClass_User;
import Utility.FrameworkConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_2_SignUp_Field_validation extends BaseClass_User
{
	FrameworkConfig cfg;
	@DataProvider(name="signup")
	public Object [][] Register_user()
	{
		Object [][] data= new Object[13][5];

		data[0][0]="";
		data[0][1]="Patil";
		data[0][2]="12345678";
		data[0][3]="varsha.patil@tech-trail.com";
		data[0][4]="Xuriti#10";

		data[1][0]="Varsha@123";
		data[1][1]="Patil";
		data[1][2]="12345678";
		data[1][3]="varsha.patil@tech-trail.com";
		data[1][4]="Xuriti#10";

		data[2][0]="Varsha";
		data[2][1]="";
		data[2][2]="12345678";
		data[2][3]="varsha.patil@tech-trail.com";
		data[2][4]="Xuriti#10";

		data[3][0]="Varsha";
		data[3][1]="Patil@111";
		data[3][2]="12345678";
		data[3][3]="varsha.patil@tech-trail.com";
		data[3][4]="Xuriti#10";

		data[4][0]="Varsha";
		data[4][1]="Patil";
		data[4][2]="";
		data[4][3]="varsha.patil@tech-trail.com";
		data[4][4]="Xuriti#10";

		data[5][0]="Varsha";
		data[5][1]="Patil";
		data[5][2]="VFR#@23567";
		data[5][3]="varsha.patil@tech-trail.com";
		data[5][4]="Xuriti#10";

		data[6][0]="Varsha";
		data[6][1]="Patil";
		data[6][2]="12345678";
		data[6][3]="varsha.patil@tech-trail.com";
		data[6][4]="Xuriti#10";

		data[7][0]="Varsha";
		data[7][1]="Patil";
		data[7][2]="8080630830";
		data[7][3]="varsha.patil@tech-trail.com";
		data[7][4]="Xuriti#10";

		data[8][0]="Varsha";
		data[8][1]="Patil";
		data[8][2]="12345678";
		data[8][3]="varsha.patiltech-trail.com";
		data[8][4]="Xuriti#10";

		data[9][0]="Varsha";
		data[9][1]="Patil";
		data[9][2]="8080630830";
		data[9][3]="varsha.patil@tech-trail.com";
		data[9][4]="Xuriti#10";

		data[10][0]="Varsha";
		data[10][1]="Patil";
		data[10][2]="8080630830";
		data[10][3]="varsha.patil@tech-trail.com";
		data[10][4]="Xuriti10";

		data[11][0]="Varsha";
		data[11][1]="Patil";
		data[11][2]="8080630830";
		data[11][3]="varsha.patil@tech-trail.com";
		data[11][4]="Xuriti#88888888";

		data[12][0]="";
		data[12][1]="";
		data[12][2]="";
		data[12][3]="";
		data[12][4]="";

		return data;

	}

     @Test(dataProvider = "signup")
	public void Sign_Up(String Firstname,String lastname,String mobileno,String email, String pwd)
	{
    	 cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
	   JSONObject request=new JSONObject();
	 //  String CONTEXTPATH = ConfigReader.getProperty("CONTEXTPATH");

	   request.put("firstName",Firstname); //first Name Blank
	   request.put("lastName",lastname);
	   request.put("mobileNumber",mobileno);
	   request.put("email",email);
	   request.put("password",pwd);

	   RequestSpecification httpRequest = RestAssured.given();
		httpRequest.contentType(ContentType.JSON);
		httpRequest.body(request.toJSONString());

	   Response response = httpRequest.post(cfg.url() + "auth/register-user");
		response.then().log().all().statusCode(200);

}
}
