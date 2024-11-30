package iuh.fit.se.techgalaxy.dto.request;

import iuh.fit.se.techgalaxy.entities.Customer;
import iuh.fit.se.techgalaxy.entities.ProductVariant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFeedbackRequest {
     String id;

     Integer feedbackRating;

     String feedbackText;

     String customerId;

     String productVariantId;
}
