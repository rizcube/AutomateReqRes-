import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class AssignmentMain {
	
	public static void main(String args[]) {
		
		RestAssured.baseURI = "https://reqres.in/";
		String list_users = given().queryParam("page", 2)
		.when()
		.get("/api/users?page=2")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println("List users");
		System.out.println(list_users);
	}

}
