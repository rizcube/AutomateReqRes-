package files;
import static io.restassured.RestAssured.*;

import java.util.List;

import org.apache.http.HttpRequest;

import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class ResourceTests {
	
	public void testAll() {
		test_get_resourceList_returns_resourceList_with_HTTP_200();
		//test_get_singleResource_notFound_returns_HTTP_200();
		//test_get_invalidResource_returns_HTTP_404();
		
	}

	private void test_get_resourceList_returns_resourceList_with_HTTP_200() {
		String[] expectedResourceNames = {"cerulean","fuchsia rose","true red","aqua sky","tigerlily","blue turquoise"};
		
		// List Resource getCall 4
		System.out.println("test_get_resourceList_returns_resourceList_with_HTTP_200() api 4 - LIST <RESOURCE> ");
		ListResources lr = given().expect().defaultParser(Parser.JSON)
				.when()
				.get("/api/unknown")
				.then().assertThat().statusCode(200)
				.extract().response().as(ListResources.class);
				
				int length = expectedResourceNames.length;
				List<files.ResourceData> ResourceData = lr.getData();
				//System.out.println(ResourceData);
				for (int i =0; i< length; i++) 
				{
					lr.getData().get(i).getName();
					if (lr.getData().get(i).getName().equalsIgnoreCase("fuchsia rose"))
					{
						System.out.println("Name >  "+ResourceData.get(i).getName());
						System.out.println("Year >  "+ResourceData.get(i).getYear());
						System.out.println("Color>  "+ResourceData.get(i).getColor());
						System.out.println("Pantone_value > "+ResourceData.get(i).getPantone_value());
					}
				
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
