package files;
// Assignment file
import io.restassured.path.json.JsonPath;

public class ReUseableMethods {

		public static JsonPath rawToJson (String response) {
			JsonPath js = new JsonPath(response);
			return js;
		}
	
}
