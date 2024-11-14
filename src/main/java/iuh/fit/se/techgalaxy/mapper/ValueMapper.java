package iuh.fit.se.techgalaxy.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import iuh.fit.se.techgalaxy.dto.request.AttributeValueRequest;
import iuh.fit.se.techgalaxy.entities.Attribute;
import iuh.fit.se.techgalaxy.entities.ProductVariant;
import iuh.fit.se.techgalaxy.entities.Value;

@Mapper(componentModel = "spring")
public interface ValueMapper {
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	Value toValue(AttributeValueRequest valueRequest, Attribute attribute, ProductVariant productVariant);
}
