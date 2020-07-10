package files;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class DelayedResponseTests {
	@Test
	private void test_get_delayed_response_returns_HTTP_200() {
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://reqres.in/").setContentType(ContentType.JSON).build();
		
		Response delayedResponse = given().queryParam("delay", 3).spec(req).expect().defaultParser(Parser.JSON)
		.when().get("/api/users?delay=3")
		.then().statusCode(200).extract().response();
		
		String delayedStringResponse = delayedResponse.asString();
		JsonPath delayedJsonResponse = Utils.rawToJson(delayedStringResponse);
		System.out.println(delayedStringResponse);
		System.out.println(delayedJsonResponse.getInt("page"));
		Object data = delayedJsonResponse.getList("data").get(0);

		String data1 = data.toString();
		JsonPath js = Utils.rawToJson(data1);
		System.out.println(js.get().toString());
		
	}
}
