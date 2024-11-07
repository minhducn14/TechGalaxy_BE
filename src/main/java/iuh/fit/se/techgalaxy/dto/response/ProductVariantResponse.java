package iuh.fit.se.techgalaxy.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantResponse {
    String id;
    String name;
    String description;
    String content;
    String avatar;
    Boolean featured;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
