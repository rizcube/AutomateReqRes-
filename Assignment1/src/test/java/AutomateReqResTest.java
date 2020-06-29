import io.cucumber.java.it.Data;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import java.util.List;

import files.Ad;
import files.ListUsers;
import files.ReUsableMethods;
import files.ReUseableMethods;
import io.restassured.path.json.JsonPath;

public class AutomateReqResTest {
	
	static String baseURI;
	public static void main(String args[]) {
		
		System.out.println("111111111111111111111111111111111");
		// get users request getCall 1

		baseURI = RestAssured.baseURI = "https://reqres.in/";
		
		ListUsers lu =given().queryParam("page", 2).expect().defaultParser(Parser.JSON)
		.when()
		.get("/api/users?page=2")
		.then().assertThat().statusCode(200)
		.extract().response().as(ListUsers.class);
		
		System.out.println("List users getCall 1");
		int page = lu.getPage();
		int per_page = lu.getPer_page();
		int total = lu.getTotal();
		int total_pages = lu.getTotal_pages();
	    
		List<files.Data> data = lu.getData();
	    String email = data.get(1).getAvatar();
	    
	    System.out.println(email);
	    
	    
		Ad ad = lu.getAd();
		System.out.println(ad.getCompany());
		System.out.println(ad.getUrl());
		System.out.println(ad.getText());

		
		System.out.println(per_page);
		
	
		
		
		/*
		
		// get single user getCall 2
		int id;
		id = 2;
		
		System.out.println("222222222222222222222222222222222222");
		String singleUser = given().queryParam("id", id)
		.when()
		.get("/api/users?page=2")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println("I am single user getCall 2 ");
		System.out.println(singleUser);
		
		
		System.out.println("333333333333333333333333333333333333333333");
		
		// single user not found getCall 3
		
		int invalidId;
		invalidId= 23;
		String singleUserNotFound = given().queryParam("id", invalidId)
		.when()
		.get("/api/users?page=2")
		.then().assertThat().statusCode(404)
		.extract().response().asString();
				
		System.out.println("User not found getCall 3");
		System.out.println(singleUserNotFound);
		
		// List Resource getCall 4
		System.out.println("4444444444444444444444444444444444444");
		String listResource = given()
		.when()
		.get("/api/unknown")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
				
		System.out.println("List Resource getCall 4");
		System.out.println(listResource);
		
		System.out.println("5555555555555555555555555555555555");
		// List single Resource not found getCall 5
		
		int resourceId = 2;
		String singleResource = given().queryParam("id", resourceId)
		.when()
		.get("/api/unknown/")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
						
		System.out.println("Single Resource getCall 5");
		System.out.println(singleResource);
		
		
		// Get request invalid Resource getCall 6
		System.out.println("666666666666666666666666666666666");
		
		int invalidResourceId = 23;
		String invalidResource = given().queryParam("id", invalidResourceId)
		.when()
		.get("/api/unknown/")
		.then().assertThat().statusCode(404)
		.extract().response().asString();
						
		System.out.println("Invalid Resource getCall 6");
		System.out.println(invalidResource);
		
		// Create user request number 7 (post request)
		System.out.println("777777777777777777777777777777777777777");
		
		
		String createUser = given().queryParam("Content-Type", "application/json")
		.body("{\n" + 
				"    \"name\": \"Rizwan\",\n" + 
				"    \"job\": \"Tester\"\n" + 
				"}").
		when().post("/api/users")
		.then().assertThat().statusCode(201)
		.header("server", "cloudflare").extract().response().asString();
	
		System.out.println(createUser);
		JsonPath js = ReUseableMethods.rawToJson(createUser);
		
		System.out.println(js);
		System.out.println("Here is Id >");
		System.out.println(js.getInt("id"));
		
		
		
		// Update user details Update/Request number 8 (put request)
		System.out.println("88888888888888888888888888888888888888888");
		
		String updateUser = given().queryParam("Content-Type", "application/json; charset=utf-8")
		.body("{\n" + 
				"    \"name\": \"morpheus\",\n" + 
				"    \"job\": \"zion resident\"\n" + 
				"}")
		.when().put("/api/users/2")
		.then().assertThat().statusCode(200).header("Server", "cloudflare")
		.extract().response().asString();
		
		System.out.println("Updated user putCall 1");
		System.out.println(updateUser);
	
		JsonPath uu = ReUseableMethods.rawToJson(updateUser);
		
		
		System.out.println("Request 8(update request) > This user was updated at >");
		System.out.println(uu.getString("updatedAt"));
		
		
	
	// Patch update Making partial changes to an existing resource / Request 9 (update request)
		System.out.println("9999999999999999999999999999999999999999999999");
	String patchUser = given().queryParam("Content-Type", "application/json; charset=utf-8")
			.body("{\n" + 
					"    \"name\": \"Rizwan Ali Khowaja\",\n" + 
					"    \"job\": \"zion nion\"\n" + 
					"}")
			.when().put("/api/users/2")
			.then().assertThat().statusCode(200).header("Server", "cloudflare")
			.extract().response().asString();
	
			
			System.out.println("Partial changes to the existing user - Request 9 (update request)");
			System.out.println(patchUser);
		
			JsonPath pu = ReUseableMethods.rawToJson(updateUser);
			System.out.println("This partial information was updated at >");
			System.out.println(pu.getString("updatedAt"));
			
	
	
	// Delete user request number 10 
	System.out.println("1000000000000000000000000000000000000000000");
	String deleteUser = given()
	.when().delete("/api/users/2")
	.then().assertThat().statusCode(204).header("Server", "cloudflare")
	.extract().response().asString();
	
	System.out.println("User has been deleted,  Request number 8");
	System.out.println(deleteUser.isBlank());
	
	
	// Post request Register user - successful - Request 11
	System.out.println("11-11-11-11-11-11-11-11-11-11");
	
	String registerSuccess = given().header("Content-Type", "application/json")
	.body("{\n" + 
			"    \"email\": \"eve.holt@reqres.in\",\n" + 
			"    \"password\": \"pistol\"\n" + 
			"}")
	.when().post("/api/register")
	.then().assertThat().statusCode(200).header("Server","cloudflare")
	.extract().response().asString();
	
	System.out.println(registerSuccess);
	
	
	
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







