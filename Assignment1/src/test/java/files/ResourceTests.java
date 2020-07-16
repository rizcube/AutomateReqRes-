package files;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpRequest;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ResourceTests {
	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://reqres.in/").setContentType(ContentType.JSON).build();
	ResponseSpecification res200 = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	ResponseSpecification res404 = new ResponseSpecBuilder().expectStatusCode(404).expectContentType(ContentType.JSON).build();
		
	@Test(dataProvider = "validIds" )
	private void test_get_singleResource_withValidId_returns_HTTP_200(int validId) {
		// Get request invalid Resource getCall 6
		System.out.println("test_get_singleResource_withValidId_returns_HTTP_200() UserStory 5 - SINGLE <RESOURCE> ");				
		String validResource = given().queryParam("id", validId).spec(req)
				.when()
				.get("/api/unknown/")
				.then().assertThat().statusCode(200).body("isEmpty()", Matchers.is(false))
				.extract().response().asString();
	}			
	
	@Test (dataProvider= "inValidIds")
	private void test_get_singleResource_with_invalidId_returns_HTTP_404(int inValidId) {
		
		int resourceId = 100;
		String singleResource = given().queryParam("id", resourceId).spec(req)
		.when()
		.get("/api/unknown/")
		.then().assertThat().statusCode(404).body("isEmpty()", Matchers.is(true))
		.extract().response().asString();
						
		System.out.println("test_get_singleResource_with_invalidId_returns_HTTP_404() UserStory 6 - SINGLE <RESOURCE> NOT FOUND ");
		System.out.println(singleResource);	
	}
	
	@DataProvider(name = "inValidIds")
	public Object[] getinValidIds() {
		return new Object[] {-1,0,13,15};
	}
	
	@DataProvider(name = "validIds")
	public Object[] getValidId() {
		return new Object[] {1,2,3,4,5,6,7,8,9,10,11,12};
	}
	
	
	@Test
	private void test_list_resource_return_HTTP_200() 
	{
		
		System.out.println("test_list_resource_return_HTTP_200() api 4a - LIST <RESOURCE> ");
		ListResources lrRes = given().log().all().spec(req)
		.when().get("/api/unknown/1")
		.then().log().all().assertThat().spec(res200)
		.extract().as(ListResources.class);
		
	
		//int expectedId = lrRes.getData().get(1).getId();
		System.out.println(lrRes);		
		//Assert.assertEquals(expectedId, 1);
		//Assert.assertEquals(actualName, "cerulean");
		////Assert.assertEquals(actualYear, "2000");
		//Assert.assertEquals(actualColor, "#98B2D1");
		//Assert.assertEquals(actualPantone, "15-4020");
		
	
	
		//System.out.println(lrRes.getData().get(validId).getId());
		// q1. when it is string how to extract data and Assert data
		// q2. when it is json how to extract data and Assert data
		// q3. if it is class how to extract data and Assert data
		//String lrStringRes =  lrRes.asString();
		//JsonPath lrJs = Utils.rawToJson(lrStringRes);
		
		//System.out.println(lrRes.getData().get(7).getId());
		
		/*ResourceData rd = new ResourceData();
		System.out.println(rd.getId());
		
		
		for (int i = 0; i <= lrRes.getTotal(); i++) {
			int actualPage = (lrRes.getPage());
			System.out.println("i is " + i);
			System.out.println("Acutal page is " + actualPage);

			//Assert.assertEquals(actualPage, i);

			//System.out.println("id is " + lrRes.getData().get(i).getId());
		}
		
	}
	
	@Test
	private void test_get_resourceList_returns_resourceList_with_HTTP_200() 
	{
		String[] expectedResourceNames = {"cerulean","fuchsia rose","true red","aqua sky","tigerlily","blue turquoise"};
		
		// List Resource getCall 4
		System.out.println("test_get_resourceList_returns_resourceList_with_HTTP_200() api 4b - LIST <RESOURCE> ");
		ListResources lr = given().spec(req)
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
	*/
}
