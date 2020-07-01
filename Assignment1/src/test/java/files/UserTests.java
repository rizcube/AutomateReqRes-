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

// test_get_single_user_returns_http_200 need to add a method
public class UserTests {
	
	
	public void testAll() {
		testFirstNames();
	}
	

	private void testFirstNames() {
		
		String[] expectedFirstNames = {"Michael","Lindsay" ,"Tobias", "Byron", "George", "Rachel" };
		
		RestAssured.baseURI = "https://reqres.in/";
		System.out.println("I am in API users class");
			
		ListUsers lu =given().queryParam("page", 2).expect().defaultParser(Parser.JSON)
		.when()
		.get("/api/users?page=2")
		.then().assertThat().statusCode(200)
		.extract().response().as(ListUsers.class);
		
		System.out.println(lu);
		
		// query: Get user details by providing their first_name
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
		
		/*
		System.out.println("************************************");
		String firstName = "Tobias";
			
			
			
			
			System.out.println("From the above list you chose " + firstName + " to get their details" + " ,please see below requested details");
				
			System.out.println("************************************");
				
		for (int i=0; i<data.size(); i++)
			{
			   if(data.get(i).getFirst_name().equalsIgnoreCase(firstName))
			    {
				   System.out.println("email > " + data.get(i).getEmail());
				   System.out.println("First Name > " + data.get(i).getFirst_name());
				   System.out.println("Last Name > " + data.get(i).getLast_name());
				   System.out.println("Avatar >  " +  data.get(i).getAvatar());
			    }
			}				
		*/
	
	
		
	
	
	}
	
}
