package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.AttributeValueRequest;
import iuh.fit.se.techgalaxy.dto.response.ValueResponse;
import iuh.fit.se.techgalaxy.entities.Attribute;
import iuh.fit.se.techgalaxy.entities.ProductVariant;
import iuh.fit.se.techgalaxy.entities.Value;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ValueMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Value toValue(AttributeValueRequest valueRequest, Attribute attribute, ProductVariant productVariant);


    ValueResponse toValueResponse(Value value);

    @Mapping(target = "type", source = "value.attribute.type")
    ValueResponse toAttributeName(Value value, String attributeName);

    ValueResponse toAttributeId(Value value, String attributeId);

}
