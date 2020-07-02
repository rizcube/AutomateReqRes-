package files;
import static io.restassured.RestAssured.*;

public class ResourceTests {
	
	public void testAll() {
		// List Resource getCall 4
		System.out.println("4444444444444444444444444444444444444");
		String listResource = given()
				.when()
				.get("/api/unknown")
				.then().assertThat().statusCode(200)
				.extract().response().asString();
						
				System.out.println("List Resource getCall 4");
				System.out.println(listResource);
	}
}
