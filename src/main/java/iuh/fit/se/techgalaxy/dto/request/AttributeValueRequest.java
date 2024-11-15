package iuh.fit.se.techgalaxy.dto.request;

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
    
}
