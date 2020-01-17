package api.tests.swager;

import api.tests.AllureListener;
import api.tests.BaseTest;
import framework.OrderModel;
import framework.enums.Order;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Log4j
@Listeners(AllureListener.class)
public class StoreTests extends BaseTest {
    String orderId;
    @Test
    public void storeInventory(){
        given()
                .spec(spec)
                .when()
                .get("/store/inventory")
                .then().statusCode(200);
    }

    @Test
    public void getStoreOrder(){
        given()
                .spec(spec)
                .when()
                .get("/store/order/{orderId}",orderId)
                .then().statusCode(200);
    }

    @Test
    public void placeOrderForPet(){
        OrderModel order = OrderModel.builder()
                .id(123)
                .petId(11)
                .quantity(100500)
                .status(Order.placed)
                .complete(true)
                .build();

        given()
                .spec(spec)
                .body(order)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200);
    }

    @Test
    public void deletePurchaseOrderById(){
        Map<String,String> date = new HashMap<>();
        date.put("orderId",orderId);
        given()
                .spec(spec)
                .body(date)
                .delete("/store/order/{orderId}",orderId)
                .then().statusCode(200);
    }
}
