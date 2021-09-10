package api.openweathermap.tests;

import api.openweathermap.commom.BasePath;
import api.openweathermap.dataprovider.DataProviderWeather;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;

public class WeatherTest extends BaseTest {

    private static final String WEATHER_SCHEMA = "schemas/weather_schema.json";
    private static final String GEOGRAPHIC_COORDENATES_SCHEMA = "schemas/geographic_coordinates_schema.json";
    private static final String SEVERAL_GROUP_IDS = "schemas/several_group_ids_schema.json";
    private static final Logger logger = LogManager.getLogger(WeatherTest.class.getName());
    /***
     * GET Request: /weather?q={city name}&appid={api key}
     * Response: JSON format
     */
    @Test(dataProviderClass = DataProviderWeather.class, dataProvider = "searchByCityName")
    public void searchByCityName_JSONFormat(String cityName) {

        given()
                .accept(ContentType.JSON)
                .queryParam("q", cityName)
                .queryParam("appid", API_KEY)
                .when()
                .get(BasePath.WEATHER.getValue())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath(WEATHER_SCHEMA))
                .body("name", equalTo(cityName));
        logger.info("Response : " + cityName);
    }


    /***
     * GET Request: /weather?q={city name}&appid={api key}&mode={xml}
     * Response: XML format
     */
    @Test(dataProviderClass = DataProviderWeather.class, dataProvider = "searchByCityName")
    public void searchByCityName_XMLFormat(String cityName) {

        given()
                .accept(ContentType.XML)
                .queryParam("q", cityName)
                .queryParam("appid", API_KEY)
                .queryParam("mode", "xml")
                .when()
                .get(BasePath.WEATHER.getValue())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("current.city.@name", equalTo(cityName));
    }


    /**
     * GET Request: /weather?id={city id}&appid={api key}
     * Response: JSON format
     */
    @Test(dataProviderClass = DataProviderWeather.class, dataProvider = "searchByCityId")
    public void searchByCityID(int cityID) {

        given()
                .accept(ContentType.JSON)
                .queryParam("id", cityID)
                .queryParam("appid", API_KEY)
                .when()
                .get(BasePath.WEATHER.getValue())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath(WEATHER_SCHEMA))
                .body("id", equalTo(cityID));
    }


    /**
     * GET Request: /weather?lat={lat}&lon={lon}&appid={api key}
     * Response: JSON format
     */
    @Test(dataProviderClass = DataProviderWeather.class, dataProvider = "searchByGeographicCoordinates")
    public void searchByGeographicCoordinates(int lat, int lon) {

        given()
                .accept(ContentType.JSON)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", API_KEY)
                .when()
                .get(BasePath.WEATHER.getValue())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath(GEOGRAPHIC_COORDENATES_SCHEMA));
    }


    /**
     * GET Request: /weather?zip={zip code},{country code}&appid={api key}
     * Response: JSON format
     */
    @Test(dataProviderClass = DataProviderWeather.class, dataProvider = "searchByZipCode")
    public void searchByZipCode(int zipCode, String countryCode) {

        given()
                .accept(ContentType.JSON)
                .queryParam("zip", String.valueOf(zipCode).concat(",").concat(countryCode))
                .queryParam("appid", API_KEY)
                .when()
                .get(BasePath.WEATHER.getValue())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath(GEOGRAPHIC_COORDENATES_SCHEMA));
    }


    /**
     * GET Request: /group?id={city IDs}&units=metric&appid={api key}
     * Response: JSON format
     */
    @Test(dataProviderClass = DataProviderWeather.class, dataProvider = "searchByGroupIDs")
    public void searchBySeveralCityIDs(int id1, int id2, int id3, String units) {

        String id = String.valueOf(id1).concat(",").concat(String.valueOf(id2)).concat(",").concat(String.valueOf(id3));

        given()
                .accept(ContentType.JSON)
                .queryParam("id", id)
                .queryParam("units", units)
                .queryParam("appid", API_KEY)
                .when()
                .get(BasePath.GROUP.getValue())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath(SEVERAL_GROUP_IDS))
                .body("list[0].id", equalTo(524901))
                .body("list[1].id", equalTo(703448))
                .body("list[2].id", equalTo(2643743));
    }
}
