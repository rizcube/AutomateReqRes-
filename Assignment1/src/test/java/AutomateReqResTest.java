import io.cucumber.java.it.Data;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import files.Ad;
import files.UserTests;
import files.ListUsers;
import files.ReUsableMethods;
import files.ReUseableMethods;
//import io.restassured.path.json.JsonPath;
import files.ResourceTests;

public class AutomateReqResTest {
	
	
	
	
	static String baseURI;
	public static void main(String args[]) {
		
		UserTests userTests = new UserTests();

		ResourceTests resourceTests = new ResourceTests();
					
	}
}







