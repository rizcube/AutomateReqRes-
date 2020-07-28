package files;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ResourceTests {
	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://reqres.in/")
			.setContentType(ContentType.JSON).build();
	ResponseSpecification res200 = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
			.build();
	ResponseSpecification res404 = new ResponseSpecBuilder().expectStatusCode(404).expectContentType(ContentType.JSON)
			.build();

	@Test(dataProvider = "validIds")
	private void test_get_singleResource_withValidId_returns_HTTP_200(int validId) {
		String validResource = given().queryParam("id", validId).spec(req).when().get("/api/unknown/").then()
				.assertThat().statusCode(200).body("isEmpty()", Matchers.is(false)).extract().response().asString();
	}

	@Test(dataProvider = "inValidIds")
	private void test_get_singleResource_with_invalidId_returns_HTTP_404(int inValidId) {

		int resourceId = 100;
		String singleResource = given().queryParam("id", resourceId).spec(req).when().get("/api/unknown/").then()
				.assertThat().statusCode(404).body("isEmpty()", Matchers.is(true)).extract().response().asString();
	}

	@Test(dataProvider = "expectedResourceList")
	private void test_list_resource_return_HTTP_200(int index, int page, int per_page, int total,
			int total_pages, int id, String name, int year, String color, String pantone_value, String company,
			String url, String text) {
		ListResources lrClassRes = given().spec(req).when().get("/api/unknown").then().assertThat().extract().response()
				.as(ListResources.class);

		// System.out.println(lrClassRes);
		int actualPage = lrClassRes.getPage();
		int actualPerPage = lrClassRes.getPer_page();
		int actualTotal = lrClassRes.getTotal();
		int actualTotal_pages = lrClassRes.getTotal_pages();
		int actualId = lrClassRes.getData().get(index).getId();
		String actualName = lrClassRes.getData().get(index).getName();
		int actualYear = lrClassRes.getData().get(index).getYear();
		String actualColor = lrClassRes.getData().get(index).getColor();
		String actualPantoneValue = lrClassRes.getData().get(index).getPantoneValue();
		String actualCompany = lrClassRes.getAd().getCompany();
		String actualUrl = lrClassRes.getAd().getUrl();
		String actualText = lrClassRes.getAd().getText();

		Assert.assertEquals(actualPage, page);
		Assert.assertEquals(actualPerPage, per_page);
		Assert.assertEquals(actualTotal, total);
		Assert.assertEquals(actualTotal_pages, total_pages);
		Assert.assertEquals(actualId, id);
		Assert.assertEquals(actualName, name);
		Assert.assertEquals(actualYear, year);
		Assert.assertEquals(actualColor, color);
		Assert.assertEquals(actualPantoneValue, pantone_value);
		Assert.assertEquals(actualCompany, company);
		Assert.assertEquals(actualUrl, url);
		Assert.assertEquals(actualText, text);
	}

	@DataProvider(name = "expectedResourceList")
	public Object[][] getExpectedResourceList() {
		return new Object[][] { { 0, 1, 6, 12, 2, 1, "cerulean", 2000, "#98B2D1", "15-4020", "StatusCode Weekly",
				"http://statuscode.org/",
				"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." },
				{ 1, 1, 6, 12, 2, 2, "fuchsia rose", 2001, "#C74375", "17-2031", "StatusCode Weekly",
						"http://statuscode.org/",
						"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." },
				{ 2, 1, 6, 12, 2, 3, "true red", 2002, "#BF1932", "19-1664", "StatusCode Weekly",
						"http://statuscode.org/",
						"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." },
				{ 3, 1, 6, 12, 2, 4, "aqua sky", 2003, "#7BC4C4", "14-4811", "StatusCode Weekly",
						"http://statuscode.org/",
						"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." },
				{ 4, 1, 6, 12, 2, 5, "tigerlily", 2004, "#E2583E", "17-1456", "StatusCode Weekly",
						"http://statuscode.org/",
						"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." },
				{ 5, 1, 6, 12, 2, 6, "blue turquoise", 2005, "#53B0AE", "15-5217", "StatusCode Weekly",
						"http://statuscode.org/",
						"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things." } };
	}

	@DataProvider(name = "inValidIds")
	public Object[] getinValidIds() {
		return new Object[] { -1, 0, 13, 15 };
	}

	@DataProvider(name = "validIds")
	public Object[] getValidId() {
		return new Object[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	}

}
