package iuh.fit.se.techgalaxy.mapper;

import org.mapstruct.Mapper;

import iuh.fit.se.techgalaxy.dto.request.AttributeRequest;
import iuh.fit.se.techgalaxy.dto.response.AttributeRespone;
import iuh.fit.se.techgalaxy.entities.Attribute;

@Mapper(componentModel = "spring")
public interface AttributeMapper {
	AttributeRespone toAttributeRespone(Attribute attribute);
	
	Attribute toAttribute(AttributeRequest attributeRequest);
}
