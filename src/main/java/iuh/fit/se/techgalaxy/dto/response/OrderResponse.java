package iuh.fit.se.techgalaxy.dto.response;

import iuh.fit.se.techgalaxy.entities.enumeration.OrderStatus;
import iuh.fit.se.techgalaxy.entities.enumeration.PaymentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String id;
    CustomerResponse customer;
    SystemUserResponse systemUser;
    String address;
    PaymentStatus paymentStatus;
    OrderStatus orderStatus;
    List<OrderDetailResponse> orderDetails;
}
