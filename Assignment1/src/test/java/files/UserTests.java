package files;
import static io.restassured.RestAssured.given;

import java.io.UnsupportedEncodingException;
//import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

// test_get_single_user_returns_http_200 need to add a method
public class UserTests extends Data {

	List<files.Data> data;
	String baseURI = RestAssured.baseURI = "https://reqres.in/";
	//RequestSpecification httpRequest = RestAssured.given();
	String resource = "/api/users?page=2";
	ListUsers lu;
	
	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://reqres.in/").setContentType(ContentType.JSON).build();
	ResponseSpecification res200 = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	ResponseSpecification res404 = new ResponseSpecBuilder().expectStatusCode(404).expectContentType(ContentType.JSON).build();
	
	@Test
	private void test_get_single_user_by_ID_returns_http_404() {
		
		System.out.println("test_get_single_user_by_ID_returns_http_404() - User Story 3  SINGLE USER NOT FOUND");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		int invalidId;
		invalidId= 230;
	
		Response singleUserNotFoundRes = given().spec(req)
		.when()
		.get("/api/users/"+invalidId+"")
		.then().spec(res404).extract().response();
	
		String singleUserNotFoundStringRes = singleUserNotFoundRes.asString();
		System.out.println(singleUserNotFoundStringRes);
	}
	
	@Test
	private void test_get_single_user_returns_http_200() {
		RestAssured.baseURI = "https://reqres.in/";
		System.out.println("test_get_single_user_returns_http_200()");
		
		Response luRes=given().queryParam("page", 2).expect().defaultParser(Parser.JSON)
				.when()
				.get(resource)
				.then().spec(res200)
				.extract().response();
				
		ListUsers luClassRes = luRes.as(ListUsers.class);
		System.out.println(luClassRes.getData().get(0).getFirst_name());
		
		
	}	
	
	@Test	
	private void testFirstNames() {
		
		String[] expectedFirstNames = {"Michael","Lindsay" ,"Tobias", "Byron", "George", "Rachel" };
		ListUsers lu =given().queryParam("page", 2).expect().defaultParser(Parser.JSON)
		.when()
		.get(resource)
		.then().assertThat().statusCode(200)
		.extract().response().as(ListUsers.class);
		
		lu.getData().get(1).getFirst_name();	
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

	@Test
	private void test_get_usersList_with_details_by_name()
	
	{
		RestAssured.baseURI = "https://reqres.in/";
		ListUsers lu =given().queryParam("page", 2).expect().defaultParser(Parser.JSON)
		.when()
		.get("/api/users?page=2")
		.then().assertThat().statusCode(200)
		.extract().response().as(ListUsers.class);
		
		System.out.println("test_get_usersList_with_details_by_name() -  User Story 1 - LIST USERS");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		 
		List<files.Data> data = lu.getData();
		
		 for (int i=0; i<data.size(); i++)
		 {
			 System.out.println("First Name > " + data.get(i).getFirst_name());
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
	
	@Test
	private void test_get_single_user_by_id() {
		// TODO Auto-generated method stub
		System.out.println("test_get_single_user_by_id() - User Story 2 SINGLE USER");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	
		ListUsers lu =given().queryParam("page", 2).expect().defaultParser(Parser.JSON)
				.when()
				.get(resource)
				.then().assertThat().statusCode(200)
				.extract().response().as(ListUsers.class);
				
				List<files.Data> data = lu.getData();
				System.out.println(data.get(4).getId());	
		
		// get single user getCall 2
				int id;
				id = 12;
				
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

	@Test
	public void test_create_user_returns_http_201()
		{	
			AddUser au = new AddUser();		
			au.setName("morpheus");
			au.setJob("leader");
		
			// Create user request number 7 (post request)
			System.out.println("test_get_single_user_by_ID_returns_http_404() - User Story 7  CREATE");
			
			Response createUser = (Response) given().header("Content-Type", "application/json")
			.body(au)
			.when().post("/api/users")
			.then().log().all().assertThat().statusCode(201).extract();
			
			String createUserResponse = createUser.asString();
			System.out.println(createUserResponse);
			
			JsonPath js = Utils.rawToJson(createUserResponse);
			//System.out.println(au.getCreatedAt());
			System.out.println(js.getInt("id"));
			// need to do the validation
	
		}
	
		@Test
		public void test_put_userDetails_returns_http_200() 
		{
			// Update user details Update/Request number 8 (put request)
			System.out.println("test_patch_userDetails_returns_http_200() User Story 8 UPDATE");
			
			AddUser pu = new AddUser();
			pu.setName("morpheus");
			pu.setJob("zion resident");
			
			Response updateUser = given().header("Content-Type", "application/json; charset=utf-8")
			.body(pu)
			.when().put("/api/users/2")
			.then().assertThat().spec(res200)
			.header("Server", "cloudflare")
			.extract().response();
			
			String updateUserStringResponse = updateUser.asString();
			System.out.println(updateUserStringResponse);
			JsonPath puJson = Utils.rawToJson(updateUserStringResponse);
			
			String actualName = puJson.getString("name");
			String actualJob = puJson.getString("job");
			String updatedAt = puJson.getString("updatedAt");
			System.out.println(updatedAt);

			Assert.assertEquals(actualName, "morpheus");
			Assert.assertEquals(actualJob, "zion resident");			
		}
	
		@Test
		public void test_patch_update_returns_http_200() 
		{
			// Patch update Making partial changes to an existing resource / Request 9 (update request)
			System.out.println("test_patch_update_returns_http_200() User Story 9 PATCH UPDATE REQUEST");
			
			AddUser pu = new AddUser();
			pu.setName("morpheus");
			pu.setJob("zion resident");
			
			
			RequestSpecification reqSpec = given().spec(req)
			.body(pu);
			
			Response patchUserRes = reqSpec.when().put("/api/users/2")
			.then().spec(res200).extract().response();
				
			String patchUserStringRes = patchUserRes.asString();
			JsonPath pu1 = Utils.rawToJson(patchUserStringRes);
			pu1.getString("updatedAt");
			pu1.getString("name");
			pu1.get("job");
				
			System.out.println(pu1.getString("name"));
			System.out.println(pu1.getString("job"));
			System.out.println(pu1.getString("updatedAt"));
				
			Assert.assertEquals(pu1.getString("name"), "morpheus");
			Assert.assertEquals(pu1.getString("job"), "zion resident");			
		}
		
		@Test
		public void test_delete_user_returns_http_204() {
			// Delete user request number 10
			System.out.println("test_patch_update_returns_http_204() User Story 10 PATCH UPDATE REQUEST");
			
			Data ud = new Data();
			ud.setEmail("eve.holt@reqres.in");
			ud.setPassword("pistol");
			
			Response deleteUserRes = given()
			.body(ud)
			.when().delete("api/users/2")
			.then().assertThat().statusCode(204).header("Server", "cloudflare").extract().response();
			
			String deleteUserStringResponse = deleteUserRes.asString();
			System.out.println(deleteUserStringResponse);
			Assert.assertEquals("", deleteUserStringResponse);
			
		}	
		@Test
		public void test_register_user_return_http_200() {
			
			System.out.println("test_register_user_return_http_200() User Story 11 REGISTER - SUCCESSFUL");
			Data ru = new Data();
			ru.setEmail("eve.holt@reqres.in");
			ru.setPassword("pistol");
			int expectedId = 4;
			String expectedToken = "QpwL5tke4Pnpja7X4";
			
			Response registerUserRes = given().spec(req)
			.body(ru)
			.when().post("/api/register")
			.then().spec(res200).extract().response();
			
			String registerUserStrRes = registerUserRes.asString();
			
			JsonPath ru1 = Utils.rawToJson(registerUserStrRes);
			int actualId = ru1.getInt("id");
			String actualToken = ru1.getString("token");
			
			System.out.println(expectedId);
			System.out.println(actualId);
			System.out.println(expectedToken);
			System.out.println(actualToken);
			System.out.println(ru.getEmail());
			System.out.println(ru.getPassword());
			
			Assert.assertEquals(expectedId, actualId);
			Assert.assertEquals(expectedToken, actualToken);
			
			System.out.println(registerUserStrRes);		
		}
		
		@Test
		public void test_register_unsuccessful_user_returns_http_400() {
			System.out.println("test_register_user_return_http_200() User Story 12 REGISTER - UNSUCCESSFUL");
			Data ru = new Data();
			ru.setEmail("sydney@fife");
			
			String registerUserUnsuccessfulRes =given().spec(req)
			.body(ru)
			.when().post("/api/register")
			.then().assertThat().statusCode(400).extract().asString();
		
			System.out.println(registerUserUnsuccessfulRes);
		
		}
		@Test
		public void test_login_successful_user_returns_http_200() {
			System.out.println("test_register_user_return_http_200() User Story 13 LOGIN - SUCCESSFUL");
			Data ls = new Data();
			ls.setEmail("eve.holt@reqres.in");
			ls.setPassword("cityslicka");
			
			String loginSuccessRes = given().spec(req)
			.body(ls)
			.when().post("/api/login")
			.then().assertThat().statusCode(200).extract().asString();
			
			System.out.println(loginSuccessRes);		
		}
		
		
		@DataProvider
		public void getData() 
		{
			new object[][] {{"eve.holt@reqres.in","cityslicka"},{"eve.holt@reqres.in","cityslicka"},{"eve.holt@reqres.in","cityslicka"}};
		}
		
		
		
		@Test
		public void test_login_unsuccessful_user_returns_http_400() {
			System.out.println("test_login_unsuccessful_user_returns_http_400 User Story 14 LOGIN - UNSUCCESSFUL");
			Data ls = new Data();
			ls.setEmail("peter@klaven");
			
			String loginUnsuccessRes = given().spec(req)
					.body(ls)
					.when().post("/api/login")
					.then().assertThat().statusCode(400).extract().asString();
			System.out.println(loginUnsuccessRes);	
		}
		
		@Test
		public void test_delayed_response_returns_http_200() throws UnsupportedEncodingException {
			
			ResourceData rd = new ResourceData();
			ListResources lr = new ListResources();
			
			int expectedPerPage = 1;
			int expectedTotal = 12;
			int expectedTotalPages = 2;
			
			System.out.println("test_delayed_response_returns_http_200() User Story 15 DELAYED RESPONSE");
			Response delayedResponse = given().spec(req).expect().defaultParser(Parser.JSON)
			.when().get("/api/users?delay=3")
			.then().assertThat().statusCode(200).extract().response();
			
			String delayedStringResponse = delayedResponse.asString();
			System.out.println(delayedStringResponse);
			JsonPath dr = Utils.rawToJson(delayedStringResponse);
			int actualPageNumber = dr.getInt("page");		
			Assert.assertEquals(expectedPerPage, actualPageNumber);
			
		}
		
}
