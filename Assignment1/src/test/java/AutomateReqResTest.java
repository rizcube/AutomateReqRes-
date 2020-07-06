import io.cucumber.java.it.Data;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import files.Ad;
import files.UserTests;
import files.ListUsers;
import files.ReUsableMethods;
import files.ReUseableMethods;
//import io.restassured.path.json.JsonPath;
import files.ResourceTests;

public class AutomateReqResTest {
	
	
	
	
	static String baseURI;
	public static void main(String args[]) {
		
		// get users request getCall 1

		UserTests userTests = new UserTests();
		userTests.testAll();
		
		ResourceTests resourceTests = new ResourceTests();
		//resourceTests.testAll();
		
		
		
		/*
		

	
	
	
	
	// Post request Register user - unsuccessful - Request 12
	
	System.out.println("12-12-12-12-12-12-12-12-12-12-12-12-12-12-12");
	
	
	
	String registerUnsuccessful = given().header("Content-Type","application/json")
	.body("{\n" + 
			"    \"email\": \"sydney@fife\"\n" + 
			"}")
	.when().post("/api/register")
	.then().assertThat().statusCode(400).header("server","cloudflare")
	.header("Content-Type", "application/json; charset=utf-8")
	.extract().response().asString();
	
	System.out.println(registerUnsuccessful);
	
	
	System.out.println("13-13-13-13-13-13-13-13-13-13-13-13-13-13-13-13");
	
	
	String loginSuccessful = given().header("Content-Type","application/json")
			.body("{\n" + 
					"    \"email\": \"eve.holt@reqres.in\",\n" + 
					"    \"password\": \"cityslicka\"\n" + 
					"}")
			.when().post("/api/login")
			.then().assertThat().statusCode(200).header("server","cloudflare")
			.header("Content-Type", "application/json; charset=utf-8")
			.extract().response().asString();
			
			System.out.println(loginSuccessful);
	
			System.out.println("14-14-14-14-14-14-14-14-14-14-14-14-14-14-14");
	
			String loginUnsuccessful = given().header("Content-Type","application/json")
					.body("{\n" + 
							"    \"email\": \"peter@klaven\"\n" + 
							"}")
					.when().post("/api/login")
					.then().assertThat().statusCode(400).header("server","cloudflare")
					.header("Content-Type", "application/json; charset=utf-8")
					.extract().response().asString();
					
					System.out.println(loginUnsuccessful);
	
	
		System.out.println("15-15-15-15-15-15-15-15-15-15-15-15-15-15-15");
	
		String delayedResponse = given()
		.when()
		.get("/api/users?delay=3")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
				
		System.out.println("This is delayed response");
		System.out.println(delayedResponse);			
					
			*/		
					
	}
}







