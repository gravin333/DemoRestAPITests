package api.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.BeforeMethod;

@Log4j
public class BaseTest {

    static final String mainUrl = "https://petstore.swagger.io/v2";

    public static RequestSpecification spec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(mainUrl)
            .build();


    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter()
        );
    }
}
