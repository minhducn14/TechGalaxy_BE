package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.request.AttributeRequest;
import iuh.fit.se.techgalaxy.dto.request.AttributeValueRequest;
import iuh.fit.se.techgalaxy.dto.request.AttributeValueUpdateRequest;
import iuh.fit.se.techgalaxy.dto.response.AttributeResponse;
import iuh.fit.se.techgalaxy.dto.response.ValueResponse;

import java.util.List;
import java.util.Set;

public interface AttributeService {

    AttributeResponse createAttribute(AttributeRequest attributeRequest);

    AttributeResponse updateAttribute(String id, AttributeRequest attributeRequest);

    AttributeResponse deleteAttribute(String id);

    AttributeResponse getAttributeById(String id);


    Set<AttributeResponse> getAllAttribute();

    boolean createValueProductVariant(String variantId, List<AttributeValueRequest> attributeValueRequest);

    boolean deleteValue(String valueId);

    ValueResponse getValueById(String valueId);

    ValueResponse updateValueProductVariant(String variantId, AttributeValueUpdateRequest attributeValueRequest);

    List<ValueResponse> getValueByNameAtri(String name);

    List<ValueResponse> getAttributeByVariantId(String variantId);
}
