package iuh.fit.se.techgalaxy.dto.request;

import iuh.fit.se.techgalaxy.dto.response.CustomerResponse;
import iuh.fit.se.techgalaxy.dto.response.SystemUserResponse;
import iuh.fit.se.techgalaxy.entities.enumeration.OrderStatus;
import iuh.fit.se.techgalaxy.entities.enumeration.PaymentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    String id;
    CustomerResponse customer;
    SystemUserResponse systemUser;
    String address;
    PaymentStatus paymentStatus;
    OrderStatus orderStatus;
}
