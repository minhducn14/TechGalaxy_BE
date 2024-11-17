package iuh.fit.se.techgalaxy.dto.response;

import java.time.LocalDateTime;

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
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFeedbackResponse {
    private String id;

    private Integer feedbackRating;

    private String feedbackText;

    private Customer customer;

    private ProductVariant productVariant;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
