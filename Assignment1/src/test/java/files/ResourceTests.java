package files;
import static io.restassured.RestAssured.*;

public class ResourceTests {
	
	public void testAll() {
		test_get_resourceList_returns_HTTP_200();
		test_get_singleResource_notFound_returns_HTTP_200();
		test_get_invalidResource_returns_HTTP_404();
		
	}

	private void test_get_resourceList_returns_HTTP_200() {
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

	
	private void test_get_singleResource_notFound_returns_HTTP_200() {

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
	}
	
	
	private void test_get_invalidResource_returns_HTTP_404() {
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
	
	
	
	}
	

}
