import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import io.restassured.path.json.JsonPath;

public class AutomateReqResTest {
	
	static String baseURI;
	public static void main(String args[]) {
		
		// get users request getCall 1

		baseURI = RestAssured.baseURI = "https://reqres.in/";
		String list_users = given().queryParam("page", 2)
		.when()
		.get("/api/users?page=2")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println("List users getCall 1");
		System.out.println(list_users);
		
		// get single user getCall 2
		int id;
		id = 2;
		
		
		String singleUser = given().queryParam("id", id)
		.when()
		.get("/api/users?page=2")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println("I am single user getCall 2 ");
		System.out.println(singleUser);
		
		
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
		
		// List Resource getCall 3
		
		String listResource = given()
		.when()
		.get("/api/unknown")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
				
		System.out.println("List Resource getCall 4");
		System.out.println(listResource);
		
		
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
		
		
		int invalidResourceId = 23;
		String invalidResource = given().queryParam("id", invalidResourceId)
		.when()
		.get("/api/unknown/")
		.then().assertThat().statusCode(404)
		.extract().response().asString();
						
		System.out.println("Invalid Resource getCall 6");
		System.out.println(invalidResource);
		
		// Create user Post Request 1
		
		String createUser = given().queryParam("Content-Type", "application/json")
		.body("{\n" + 
				"    \"name\": \"Rizwan\",\n" + 
				"    \"job\": \"Tester\"\n" + 
				"}").
		when().log().all().post("/api/users")
		.then().log().all().assertThat().statusCode(201)
		.header("server", "cloudflare").extract().response().asString();
	
		System.out.println(createUser);
		JsonPath js = new JsonPath(createUser);
		System.out.println(js.getInt("id"));
		
	}

}
