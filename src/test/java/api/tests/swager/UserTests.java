package api.tests.swager;

import api.tests.BaseTest;
import framework.UserModel;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class UserTests extends BaseTest {

    @Test
    public void createUser(){
        UserModel user = UserModel.builder()
                .email("test@test.te")
                .firstName("first")
                .id(666)
                .lastName("last")
                .password("12345")
                .phone("12345679")
                .username("tester")
                .status(0)
                .build();

       given()
                .spec(spec)
                .body(user)
                .when()
                .post("/user")
                .then().statusCode(200);
    }

}
