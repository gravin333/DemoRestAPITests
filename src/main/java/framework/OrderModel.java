package framework;

import framework.enums.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderModel {
    int id;
    int petId;
    int quantity;
    String shipDate;
    Order status;
    boolean complete;
}

