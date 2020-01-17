package api.tests.swager;

import api.tests.AllureListener;
import api.tests.BaseTest;
import framework.PetResponseModel;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j
@Listeners(AllureListener.class)
public class PetTests extends BaseTest {

    String petId;

    @Test(dependsOnMethods= {"addNewPetToTheStore"})
    public void findPetById(){
        given()
                .spec(spec)
                .when()
                .get("/pet/{petId}",petId)
                .then()
                .statusCode(200);
    }

    @Test
    public void findPetByIdIncorrect(){
        given()
                .spec(spec)
                .when()
                .get("/pet/test")
                .then()
                .statusCode(404);
    }

    @Test
    public void addNewPetToTheStore(){
        petId = given()
                .spec(spec)
                .when()
                .body("{}")
                .post("/pet")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("id");
    }

    @Test(dependsOnMethods = {"addNewPetToTheStore"})
    public void updateAnExistingPet(){
        Map<String,String> date = new HashMap<>();
        date.put("name", "doggie");
        date.put("id", petId);
        PetResponseModel responseModel = given()
                .spec(spec)
                .when()
                .body(date)
                .put("/pet")
                .then()
                .extract().as(PetResponseModel.class);

       assertThat("Pet name is not correct",responseModel.getName(),is("doggie"));
    }

    @Test(priority = 5,dependsOnMethods = {"updateAnExistingPet"})
    public void deletePet(){
        given()
                .spec(spec)
                .when()
                .delete("/pet/{petId}",petId)
                .then()
                .statusCode(200);
    }

    @Test(dependsOnMethods = {"addNewPetToTheStore"})
    public void uploadImage () throws URISyntaxException {

        File file = new File(ClassLoader.getSystemResource("ko.jpg").toURI());

        Map<String,String> date = new HashMap<>();
        date.put("name", "doggie");

        given()
                .spec(spec)
                .contentType("multipart/form-data")
                .multiPart(file)
                .when()
                .post("/pet/{petId}/uploadImage",petId)
                .then()
                .statusCode(200);
    }

    @Test
    public void findPestBySoldStatus(){
        Map<String,String> date = new HashMap<>();
        date.put("status", "sold");

        given()
                .spec(spec)
                .queryParam("status", "sold")
                .when()
                .get("/pet/findByStatus")
                .then().statusCode(200);
    }
}
