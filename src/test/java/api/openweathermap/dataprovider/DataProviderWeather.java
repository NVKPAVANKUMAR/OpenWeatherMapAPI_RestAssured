package api.openweathermap.dataprovider;

import org.testng.annotations.DataProvider;

public class DataProviderWeather {
	
	@DataProvider(name = "searchByCityName")
	public static Object[][] cityName() {
		return new Object[][] {{"London"}};
	}
	
	@DataProvider(name = "searchByCityId")
	public static Object[][] cityID() {
		return new Object[][] {{2172797}};
	}
	
	@DataProvider(name = "searchByGeographicCoordinates")
	public static Object[][] searchByGeographicCoordinates() {
		return new Object[][] {{35, 139}};
	}
	
	@DataProvider(name = "searchByZipCode")
	public static Object[][] searchByZipCode() {
		return new Object[][] {{94040, "us"}};
	}
	
	@DataProvider(name = "searchByGroupIDs")
	public static Object[][] searchByGroupIDs() {
		return new Object[][] {{524901, 703448, 2643743, "metric"}};
	}
}
