package files;
import static io.restassured.RestAssured.*;

import java.util.List;

import io.restassured.parsing.Parser;

public class ResourceTests {
	
	public void testAll() {
		test_get_resourceList_returns_HTTP_200();
		//test_get_singleResource_notFound_returns_HTTP_200();
		//test_get_invalidResource_returns_HTTP_404();
		
	}

	private void test_get_resourceList_returns_HTTP_200() {
		String[] expectedResourceNames = {"cerulean","fuchsia rose","true red","aqua sky","tigerlily","blue turquoise"};
		
		// List Resource getCall 4
		System.out.println("test_get_resourceList_returns_HTTP_200() api 4 - LIST <RESOURCE> ");
		ListResources lr = given().expect().defaultParser(Parser.JSON)
				.when()
				.get("/api/unknown")
				.then().assertThat().statusCode(200)
				.extract().response().as(ListResources.class);
								
				System.out.println("List Resource getCall 4");
				System.out.println(lr.getPage());
				System.out.println(lr.getData().get(0).getName());
				
		
				
				int length = expectedResourceNames.length;
				System.out.println("I am the object of resource");
				
				List<files.ResourceData> ResourceData = System.out.println(lr.getData());
				
				for (int i =0; i< length; i++) {
					lr.getData().get(0).getName();
				}
				
				
				
	
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
