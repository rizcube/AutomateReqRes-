package files;
import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.hamcrest.Matchers.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.lessThan;

public class DelayedResponseTests {
	
	@Test
	private void test_Response_Time_returns_HTTP_200() {
		
		System.out.println("test_get_delayed_response_returns_HTTP_200() User Story 15 Delayed Response ");
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://reqres.in/").setContentType(ContentType.JSON).build();
		
		ValidatableResponse delayedRes =
		given().log().all().queryParam("delay", 3).spec(req).expect().defaultParser(Parser.JSON)
		.when().get("/api/users")
		.then().log().all().assertThat().time(lessThan(3L), TimeUnit.SECONDS);
	}
	
	
	
	@Test
	private void test_get_delayed_response_returns_HTTP_200() {

		System.out.println("test_get_delayed_response_returns_HTTP_200() User Story 15 Delayed Response ");
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://reqres.in/").setContentType(ContentType.JSON).build();
		
		Response delayedRes =given().queryParam("delay", 3).spec(req).expect().defaultParser(Parser.JSON)
		.when().get("/api/users?delay=3")
		.then().log().all().statusCode(200).extract().response();
		//Hard assertion
		//delayedRes.then().body("page", equalTo(1)).body("per_page", equalTo(6));
		// soft Assertion
		//delayedRes.then().body("page", equalTo(1),"per_page", equalTo(6));
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(delayedRes.path("page"), 1);
		softAssert.assertEquals(delayedRes.path("per_page"), 6);
		softAssert.assertEquals(delayedRes.path("total"), 12);
		softAssert.assertEquals(delayedRes.path("total_pages"), 2);
		
		softAssert.assertAll();
		
		String delayedStringRes = delayedRes.asString();
		JsonPath js = Utils.rawToJson(delayedStringRes);
		System.out.println(js.get("page"));
		List<Data> dataList = js.getList("data");
		System.out.println(dataList);
		
		
		//DelayedResponse dr = new DelayedResponse();
		//System.out.println(dr.getPage());
		System.out.println(dataList.get(0).getEmail());
		
	
		
		//Assert.assertEquals(delayedRes.getTimeIn(TimeUnit.SECONDS)<=1, "3.12 s");
		//System.out.println(delayedRes);
		
		
		/*
		System.out.println(">>>>>" + a);
		System.out.println(delayedResponse.getTimeIn(TimeUnit.SECONDS));
		System.out.println(delayedResponse.time());
		System.out.println(delayedResponse.getTime());
		//System.out.println(delayedResponsetime(lessThan(2000)));		
		String delayedStringResponse = delayedResponse.asString();
		JsonPath delayedJsonResponse = Utils.rawToJson(delayedStringResponse);
		System.out.println(delayedStringResponse);
		System.out.println(delayedJsonResponse.getInt("page"));
		Object data = delayedJsonResponse.getList("data").get(0);

		
		String data1 = data.toString();
		//JsonPath js = Utils.rawToJson(data1);
		//System.out.println(js.get().toString());
		*/
	}
}
