package files;
import static io.restassured.RestAssured.*;

import java.util.List;

import org.apache.http.HttpRequest;
import org.hamcrest.Matchers;
import org.junit.Assert;

import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class ResourceTests {
	
	public void testAll() {
		//test_get_resourceList_returns_resourceList_with_HTTP_200();
		//test_get_singleResource_with_invalidId_returns_HTTP_404();
		test_get_singleResource_withValidId_returns_HTTP_200();
		
	}
	String lr;
	
	private void test_get_resourceList_returns_resourceList_with_HTTP_200() {
		String[] expectedResourceNames = {"cerulean","fuchsia rose","true red","aqua sky","tigerlily","blue turquoise"};
		
		// List Resource getCall 4
		System.out.println("test_get_resourceList_returns_resourceList_with_HTTP_200() api 4 - LIST <RESOURCE> ");
		ListResources lr = given().expect().defaultParser(Parser.JSON)
				.when()
				.get("/api/unknown")
				.then().assertThat().statusCode(200)
				.extract().response().as(ListResources.class);
				
				int lengthExpectedList = expectedResourceNames.length;
				List<files.ResourceData> ResourceData = lr.getData();
				//System.out.println(ResourceData);
				for (int i =0; i< lengthExpectedList; i++) 
				{
					String resourceName = expectedResourceNames[i];
					lr.getData().get(i).getName();
					if (lr.getData().get(i).getName().equalsIgnoreCase(resourceName))
					{
						System.out.println("Name >  "+ResourceData.get(i).getName());
						System.out.println("Year >  "+ResourceData.get(i).getYear());
						System.out.println("Color>  "+ResourceData.get(i).getColor());
						System.out.println("Pantone_value > "+ResourceData.get(i).getPantone_value());
					}
				
				
			
				for (int j= 0; j< lengthExpectedList; j++) 
				{
					if (ResourceData.get(j).getName().equalsIgnoreCase(resourceName))
					{
						System.out.println(ResourceData.get(j).getName());
					}
				}
				
	}
				
	}

	private void test_get_singleResource_with_invalidId_returns_HTTP_404() {
		
		int resourceId = 100;
		String singleResource = given().queryParam("id", resourceId)
		.when()
		.get("/api/unknown/")
		.then().assertThat().statusCode(404).body("isEmpty()", Matchers.is(true))
		.extract().response().asString();
						
		System.out.println("test_get_singleResource_with_invalidId_returns_HTTP_404() UserStory 6 - SINGLE <RESOURCE> NOT FOUND ");
		System.out.println(singleResource);
		
	}
	
	
	private void test_get_singleResource_withValidId_returns_HTTP_200() {
		// Get request invalid Resource getCall 6
	
		
		int[] validIds = {1,2,3,4,5,6,7,8,9,10,11,12};
		//int validResourceId = 2;
		System.out.println("test_get_singleResource_withValidId_returns_HTTP_200() UserStory 5 - SINGLE <RESOURCE> ");
		for (int i= 1; i<=validIds.length; i++) {
			
			String validResource = given().queryParam("id", i)
			.when()
			.get("/api/unknown/")
			.then().assertThat().statusCode(200).body("isEmpty()", Matchers.is(false))
			.extract().response().asString();
			System.out.println(validResource);
			System.out.println("");
			
		}
	
	}				
}
