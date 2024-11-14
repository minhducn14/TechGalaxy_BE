package iuh.fit.se.techgalaxy.dto.request;

<<<<<<< HEAD
import iuh.fit.se.techgalaxy.entities.ProductVariant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttributeValueRequest {
	
	
=======
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttributeValueRequest {
    String value;
    String attributeId;
>>>>>>> e938e877566ee56d3dd67571f38771b664cd2713
}
