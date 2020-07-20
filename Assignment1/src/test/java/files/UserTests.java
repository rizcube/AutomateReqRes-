package files;

import static io.restassured.RestAssured.given;

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
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

// test_get_single_user_returns_http_200 need to add a method
public class UserTests extends Data {

	String baseURI = RestAssured.baseURI = "https://reqres.in/";
	ListUsers lu;

	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://reqres.in/")
			.setContentType(ContentType.JSON).build();
	ResponseSpecification res200 = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
			.build();
	ResponseSpecification res404 = new ResponseSpecBuilder().expectStatusCode(404).expectContentType(ContentType.JSON)
			.build();

	@Test(dataProvider = "expectedInValidIds")
	private void test_get_single_user_by_invalidId_returns_http_404_UStory3(int invalidId) {

		Response singleUserNotFoundRes = given().spec(req).when().get("/api/users/" + invalidId + "").then()
				.spec(res404).extract().response();
		String singleUserNotFoundStringRes = singleUserNotFoundRes.asString();
		Assert.assertEquals("{[]}", "{[]}");
	}

	@Test(dataProvider = "expectedSingleUserdata")
	private void test_get_single_user_returns_http_200_UStory1(int id, String email, String first_name,
			String last_name, String avatar, String company, String url, String text) {
		RestAssured.baseURI = "https://reqres.in/";

		GetSingleUser gsUser = given().queryParam("page", id).expect().defaultParser(Parser.JSON).when()
				.get("/api/users/" + id).then().spec(res200).extract().response().as(GetSingleUser.class);

		Assert.assertEquals(gsUser.getData().getId(), id);
		Assert.assertEquals(gsUser.getData().getEmail(), email);
		Assert.assertEquals(gsUser.getData().getFirst_name(), first_name);
		Assert.assertEquals(gsUser.getData().getLast_name(), last_name);
		Assert.assertEquals(gsUser.getData().getAvatar(), avatar);
		Assert.assertEquals(gsUser.getAd().getCompany(), company);
		Assert.assertEquals(gsUser.getAd().getUrl(), url);
		Assert.assertEquals(gsUser.getAd().getText(), text);
	}

	@Test(dataProvider = "expectedUserName&IdPg2")
	private void testFirstNames_UStory2(int index, int id, String first_name) {

		ListUsers lu = given().queryParam("page", 2).expect().defaultParser(Parser.JSON).when().get("/api/users?page=2")
				.then().assertThat().statusCode(200).extract().response().as(ListUsers.class);
		lu.getData().get(index).getFirst_name();

		Assert.assertEquals(lu.getData().get(index).getFirst_name(), first_name);
		Assert.assertEquals(lu.getData().get(index).getId(), id);

	}

	@Test
	public void test_create_user_returns_http_201_UStory7() {
		AddUser au = new AddUser();
		au.setName("morpheus");
		au.setJob("leader");

		Response createUser = (Response) given().header("Content-Type", "application/json").body(au).when()
				.post("/api/users").then().assertThat().statusCode(201).extract().body();

		String createUserResponse = createUser.asString();
		JsonPath js = Utils.rawToJson(createUserResponse);
	}

	@Test
	public void test_put_userDetails_returns_http_200_UStory8() {
		AddUser pu = new AddUser();
		pu.setName("morpheus");
		pu.setJob("zion resident");

		Response updateUser = given().header("Content-Type", "application/json; charset=utf-8").body(pu).when()
				.put("/api/users/2").then().assertThat().spec(res200).header("Server", "cloudflare").extract()
				.response();

		String updateUserStringResponse = updateUser.asString();
		JsonPath puJson = Utils.rawToJson(updateUserStringResponse);

		String actualName = puJson.getString("name");
		String actualJob = puJson.getString("job");
		String updatedAt = puJson.getString("updatedAt");

		Assert.assertEquals(actualName, "morpheus");
		Assert.assertEquals(actualJob, "zion resident");
	}

	@Test
	public void test_patch_update_returns_http_200_UStory9() {
		// Patch update Making partial changes to an existing resource / Request 9
		// (update request)
		System.out.println("test_patch_update_returns_http_200() User Story 9 PATCH UPDATE REQUEST");

		AddUser pu = new AddUser();
		pu.setName("morpheus");
		pu.setJob("zion resident");

		RequestSpecification reqSpec = given().spec(req).body(pu);

		Response patchUserRes = reqSpec.when().put("/api/users/2").then().spec(res200).extract().response();

		String patchUserStringRes = patchUserRes.asString();
		JsonPath pu1 = Utils.rawToJson(patchUserStringRes);
		pu1.getString("updatedAt");
		String actualName = pu1.getString("name");
		String actualJob = pu1.get("job");

		Assert.assertEquals(actualName, "morpheus");
		Assert.assertEquals(actualJob, "zion resident");
	}

	@Test
	public void test_delete_user_returns_http_204_UStory10() {
		// Delete user request number 10
		System.out.println("test_patch_update_returns_http_204() User Story 10 PATCH UPDATE REQUEST");

		Data ud = new Data();
		ud.setEmail("eve.holt@reqres.in");
		ud.setPassword("pistol");

		Response deleteUserRes = given().body(ud).when().delete("api/users/2").then().assertThat().statusCode(204)
				.header("Server", "cloudflare").extract().response();

		String deleteUserStringResponse = deleteUserRes.asString();
		System.out.println(deleteUserStringResponse);
		Assert.assertEquals("", deleteUserStringResponse);
	}

	@Test
	public void test_register_user_return_http_200_UStory11() {
		Data ru = new Data();
		ru.setEmail("eve.holt@reqres.in");
		ru.setPassword("pistol");
		int expectedId = 4;
		String expectedToken = "QpwL5tke4Pnpja7X4";

		Response registerUserRes = given().spec(req).body(ru).when().post("/api/register").then().spec(res200).extract()
				.response();

		String registerUserStrRes = registerUserRes.asString();

		JsonPath ru1 = Utils.rawToJson(registerUserStrRes);
		int actualId = ru1.getInt("id");
		String actualToken = ru1.getString("token");

		Assert.assertEquals(expectedId, actualId);
		Assert.assertEquals(expectedToken, actualToken);
	}

	@Test
	public void test_register_unsuccessful_user_returns_http_400_UStory12() {

		Data ru = new Data();
		ru.setEmail("sydneey@fife");

		ResponseBodyExtractionOptions registerUnsuccessRes = given().spec(req).body(ru).when().post("/api/register")
				.then().assertThat().statusCode(400).extract().response().body();

		String registerUnsuccessStringRes = registerUnsuccessRes.asString();
		// System.out.println(registerUnsuccessStringRes);

		JsonPath regUserJson = Utils.rawToJson(registerUnsuccessStringRes);
		String actualError = (regUserJson.getString("error"));
		String expectedError = "Missing password";
		Assert.assertEquals(actualError, expectedError);

	}

	@Test(dataProvider = "loginDetails")
	public void test_login_successful_user_returns_http_200_UStory13(String email, String password) {
		Data ls = new Data();
		ls.setEmail(email);
		ls.setPassword(password);

		ResponseBody loginSuccessRes = given().spec(req).body(ls).when().post("/api/login").then().assertThat()
				.statusCode(200).extract().response().body();

		String loginSuccessStringRes = loginSuccessRes.asString();
		JsonPath loginSuccessJsonRes = Utils.rawToJson(loginSuccessStringRes);
		String actualToken = loginSuccessJsonRes.getString("token");
		String expectedToken = "QpwL5tke4Pnpja7X4";
		Assert.assertEquals(actualToken, expectedToken);
	}

	@Test(dataProvider = "loginUnsuccessful")
	public void test_login_unsuccessful_user_returns_http_400_UStory14(String email) {

		Data ls = new Data();
		ls.setEmail(email);

		String loginUnsuccessRes = given().spec(req).body(ls).when().post("/api/login").then().assertThat()
				.statusCode(400).extract().asString();

		JsonPath loginUnsuccessJsonRes = Utils.rawToJson(loginUnsuccessRes);
		String actualError = loginUnsuccessJsonRes.getString("error");
		String expectedError = "Missing password";
		Assert.assertEquals(actualError, expectedError);
	}

	@DataProvider(name = "loginUnsuccessful")
	public Object[] get() {
		return new Object[] { "peter@klaven", "rizcube@gmail.com" };
	}

	@DataProvider(name = "expectedInValidIds")
	public Object[] getinValidIds() {
		return new Object[] { -1, 0, 13, 15 };
	}

	@DataProvider(name = "validIds")
	public Object[] getValidId() {
		return new Object[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	}

	@DataProvider(name = "expectedUserId")
	public Object[] getUserId() {
		return new Object[] { 1, 2, 3, 4, 5, 6 };
	}

	@DataProvider(name = "expectedUserName&IdPg2")
	public Object[][] getUserNamePg2() {
		return new Object[][] { { 0, 7, "Michael" }, { 1, 8, "Lindsay" }, { 2, 9, "Tobias" }, { 3, 10, "Byron" },
				{ 4, 11, "George" }, { 5, 12, "Rachel" } };
	}

	@DataProvider(name = "expectedSingleUserdata")
	public Object[][] getUserdata() {

		return new Object[][] { { 1, "george.bluth@reqres.in", "George", "Bluth",
				"https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg", "StatusCode Weekly",
				"http://statuscode.org/",
				"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." },
				{ 2, "janet.weaver@reqres.in", "Janet", "Weaver",
						"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg", "StatusCode Weekly",
						"http://statuscode.org/",
						"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." },
				{ 3, "emma.wong@reqres.in", "Emma", "Wong",
						"https://s3.amazonaws.com/uifaces/faces/twitter/olegpogodaev/128.jpg", "StatusCode Weekly",
						"http://statuscode.org/",
						"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." },
				{ 12, "rachel.howell@reqres.in", "Rachel", "Howell",
						"https://s3.amazonaws.com/uifaces/faces/twitter/hebertialmeida/128.jpg", "StatusCode Weekly",
						"http://statuscode.org/",
						"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." } };
	}

	@DataProvider(name = "loginDetails")
	public Object[][] getData() {
		return new Object[][] { { "eve.holt@reqres.in", "cityslicka" }, { "eve.holt@reqres.in", "licka" },
				{ "eve.holt@reqres.in", "city" } };

	}

}
