package files;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class DelayedResponseTests {
	@Test
	private void test_Response_Time_returns_HTTP_200_UStory15() {

		System.out.println("test_get_delayed_response_returns_HTTP_200() User Story 15 Delayed Response ");

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://reqres.in/")
				.setContentType(ContentType.JSON).build();
		ResponseSpecification res200 = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();

		ValidatableResponse delayedRes = given().queryParam("delay", 2).spec(req).expect().defaultParser(Parser.JSON)
				.when().get("/api/users").then().assertThat().time(lessThan(4L), TimeUnit.SECONDS);
	}

	@Test(dataProvider = "expectedDelayedRes")
	private void test_get_delayed_response_returns_HTTP_200_UStory15(int index, int page, int per_page, int total,
			int total_pages, int id, String email, String first_name, String last_name, String avatar, String company,
			String url, String text) {

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://reqres.in/")
				.setContentType(ContentType.JSON).build();

		DelayedResponse delayedClassRes = given().queryParam("delay", 3).spec(req).expect().defaultParser(Parser.JSON)
				.when().get("/api/users?delay=3").then().extract().response().as(DelayedResponse.class);

		int actualPage = delayedClassRes.getPage();
		int actualPer_page = delayedClassRes.getPer_page();
		int actualTotal = delayedClassRes.getTotal();
		int actualTotal_pages = delayedClassRes.getTotal_pages();
		int actualId = delayedClassRes.getData().get(0).getId();
		String actualEmail = delayedClassRes.getData().get(index).getEmail();
		String actualFirst_name = delayedClassRes.getData().get(index).getFirst_name();
		String actualLast_name = delayedClassRes.getData().get(index).getLast_name();
		String actualAvatar = delayedClassRes.getData().get(index).getAvatar();
		String actualCompany = delayedClassRes.getAd().getCompany();
		String actualUrl = delayedClassRes.getAd().getUrl();
		String actualText = delayedClassRes.getAd().getText();

		Assert.assertEquals(actualPage, page);
		Assert.assertEquals(actualPer_page, per_page);
		Assert.assertEquals(actualTotal, total);
		Assert.assertEquals(actualTotal_pages, total_pages);
		Assert.assertEquals(actualId, id);
		Assert.assertEquals(actualEmail, email);
		Assert.assertEquals(actualFirst_name, first_name);
		Assert.assertEquals(actualLast_name, last_name);
		Assert.assertEquals(actualAvatar, avatar);
		Assert.assertEquals(actualCompany, company);
		Assert.assertEquals(actualUrl, url);
		Assert.assertEquals(actualText, text);

	}

	@DataProvider(name = "expectedDelayedRes")
	public Object[][] getDelayedResponse() {
		return new Object[][] { { 0, 1, 6, 12, 2, 1, "george.bluth@reqres.in", "George", "Bluth",
				"https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg", "StatusCode Weekly",
				"http://statuscode.org/",
				"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." },
				{ 1, 1, 6, 12, 2, 1, "janet.weaver@reqres.in", "Janet", "Weaver",
						"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg", "StatusCode Weekly",
						"http://statuscode.org/",
						"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." },
				{ 2, 1, 6, 12, 2, 1, "emma.wong@reqres.in", "Emma", "Wong",
						"https://s3.amazonaws.com/uifaces/faces/twitter/olegpogodaev/128.jpg", "StatusCode Weekly",
						"http://statuscode.org/",
						"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." },
				{ 3, 1, 6, 12, 2, 1, "eve.holt@reqres.in", "Eve", "Holt",
						"https://s3.amazonaws.com/uifaces/faces/twitter/marcoramires/128.jpg", "StatusCode Weekly",
						"http://statuscode.org/",
						"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." },
				{ 4, 1, 6, 12, 2, 1, "charles.morris@reqres.in", "Charles", "Morris",
						"https://s3.amazonaws.com/uifaces/faces/twitter/stephenmoon/128.jpg", "StatusCode Weekly",
						"http://statuscode.org/",
						"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." },
				{ 5, 1, 6, 12, 2, 1, "tracey.ramos@reqres.in", "Tracey", "Ramos",
						"https://s3.amazonaws.com/uifaces/faces/twitter/bigmancho/128.jpg", "StatusCode Weekly",
						"http://statuscode.org/",
						"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." } };
	}
}
