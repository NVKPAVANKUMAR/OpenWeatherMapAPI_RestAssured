package api.openweathermap.tests;

import org.testng.annotations.BeforeClass;
import api.openweathermap.commom.Configuration;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;

public class BaseTest {
	
	protected static final String API_KEY = "439d4b804bc8187953eb36d2a8c26a02";
		
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = Configuration.getUri();
		enableLoggingOfRequestAndResponseIfValidationFails();
	}
}
