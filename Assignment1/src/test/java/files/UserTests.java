package files;
/*
UserTests
addUser
createUser
listUser

ResourceTests
listResources
singleResource
singleResourceNotFound

*/
import static io.restassured.RestAssured.given;
//import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

// test_get_single_user_returns_http_200 need to add a method
public class UserTests {
	
	
	public void testAll() {
		
		//testFirstNames();
		test_get_single_user_returns_http_200();
		test_get_single_user_details_by_name();
		test_get_single_user_by_id();

	}
	

	List<files.Data> data;
	String baseURI = RestAssured.baseURI = "https://reqres.in/";
	RequestSpecification httpRequest = RestAssured.given();
	String resource = "/api/users?page=2";
	ListUsers lu;
	
	
	private void test_get_single_user_returns_http_200() {
		
		System.out.println("test_get_single_user_returns_http_200()");
		ListUsers lu =given().queryParam("page", 2).expect().defaultParser(Parser.JSON)
				.when()
				.get(resource)
				.then().assertThat().statusCode(200)
				.extract().response().as(ListUsers.class);
		
		Response statusCode = httpRequest.get();
		System.out.println("Expected Status code > " + statusCode.statusCode());
		System.out.println("");
		System.out.println("");
	
	}	
	
	
	private void testFirstNames() {
		
		String[] expectedFirstNames = {"Michael","Lindsay" ,"Tobias", "Byron", "George", "Rachel" };
		
		//String baseURI = RestAssured.baseURI = "https://reqres.in/";
		System.out.println("I am in API users class");
			
		ListUsers lu =given().queryParam("page", 2).expect().defaultParser(Parser.JSON)
		.when()
		.get(resource)
		.then().assertThat().statusCode(200)
		.extract().response().as(ListUsers.class);
		
	    ArrayList<String> actualFristNamesList = new ArrayList<String>();
		
	    List<files.Data> data = lu.getData();

		for (int i=0; i<data.size(); i++)
			{
				Assert.assertTrue(actualFristNamesList.add(data.get(i).getFirst_name()));
			
			}	
		
		List<String> expectedFirstNamesList = Arrays.asList(expectedFirstNames);
		System.out.println("Expected First Names List >" + expectedFirstNamesList);
		System.out.println("Actual First Names List   >"+ actualFristNamesList);
		Assert.assertTrue(actualFristNamesList.equals(expectedFirstNamesList));
	}
	
	
	private void test_get_single_user_details_by_name()
	
	{
		RestAssured.baseURI = "https://reqres.in/";
		ListUsers lu =given().queryParam("page", 2).expect().defaultParser(Parser.JSON)
		.when()
		.get("/api/users?page=2")
		.then().assertThat().statusCode(200)
		.extract().response().as(ListUsers.class);
		
		System.out.println("test_get_single_user_details_by_name()");
		System.out.println("+++++++++++++++++++++++++++++++++++++++");
		
		// query: Get user details by providing their first_name
	    
		List<files.Data> data = lu.getData();
		//System.out.println(data);
		
		 for (int i=0; i<data.size(); i++)
		 {
			 //System.out.println("First Name > " + data.get(i).getFirst_name());
		 }
		
		 
		String[] expectedFirstNames = {"Michael","Lindsay" ,"Tobias", "Byron", "George", "Rachel" }; 
		int length = expectedFirstNames.length;
		
		for (int j=0; j<length; j++) 
		{
			
			String firstName = expectedFirstNames[j];
			//System.out.println("************************************");
			System.out.println("This is item "+ j + "> " + firstName + " in the list,please see below requested details");
		
			System.out.println("---------------------------------------");
		
			for (int i=0; i<data.size(); i++)
			{
				if(data.get(i).getFirst_name().equalsIgnoreCase(firstName))
		    	{
		    		System.out.println("email > " + data.get(i).getEmail());
		    		System.out.println("First Name > " + data.get(i).getFirst_name());
		    		System.out.println("Last Name > " + data.get(i).getLast_name());
		    		System.out.println("Avatar >  " +  data.get(i).getAvatar());
		    		System.out.println("");
		    		
		    	}
			}
		
		}
	    
	}
	
	
	
	
	private void test_get_single_user_by_id() {
		// TODO Auto-generated method stub
		System.out.println("test_get_single_user_by_id()");
	
		ListUsers lu =given().queryParam("page", 2).expect().defaultParser(Parser.JSON)
				.when()
				.get(resource)
				.then().assertThat().statusCode(200)
				.extract().response().as(ListUsers.class);
				
				
				System.out.println("+++++++++++++++++++++++++++++++++++");
				List<files.Data> data = lu.getData();
				System.out.println(data.get(4).getId());
		
		
		
		// get single user getCall 2
				int id;
				id = 11;
				
				String singleUser = given().queryParam("id", id)
				.when()
				.get("/api/users?page=2")
				.then().assertThat().statusCode(200)
				.extract().response().asString();
				
				System.out.println("I am single user getCall 2 ");
				System.out.println(singleUser);
				Data d = new Data();
				System.out.println(d.getAvatar());
				
	
	
	}

	
	
	
	
	
	
	
	
	
	
}
