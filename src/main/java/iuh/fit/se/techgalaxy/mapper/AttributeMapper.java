package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.AttributeRequest;
import iuh.fit.se.techgalaxy.dto.response.AttributeResponse;
import iuh.fit.se.techgalaxy.entities.Attribute;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AttributeMapper {
    AttributeResponse toAttributeResponse(Attribute attribute);

    Attribute toAttribute(AttributeRequest attributeRequest);

    void updateAttributeFromRequest(@MappingTarget Attribute attribute, AttributeRequest attributeRequest);
}
