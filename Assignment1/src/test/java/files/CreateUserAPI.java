package files;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class CreateUserAPI {
	// test_post_user_returns_http_200 need to add a method
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://reqres.in/";
		
		AddUser au = new AddUser();
		au.setName("morpheus");
		au.setJob("Leader");
		
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

	}

}
