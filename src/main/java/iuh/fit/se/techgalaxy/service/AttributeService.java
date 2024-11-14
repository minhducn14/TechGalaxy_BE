package iuh.fit.se.techgalaxy.service;

import java.util.Set;

import iuh.fit.se.techgalaxy.dto.request.AttributeRequest;
import iuh.fit.se.techgalaxy.dto.response.AttributeResponse;

public interface AttributeService {

	AttributeResponse createAttribute(AttributeRequest attributeRequest);

	AttributeResponse updateAttribute(String id, AttributeRequest attributeRequest);

	AttributeResponse deleteAttribute(String id);

	AttributeResponse getAttributeById(String id);
	
	Set<AttributeResponse> getAllAttribute();
}
//find  variant if true
// tao vong lap de map tu request sang value phai co mapper de lam
// sau khi tao xong thi save vao csdl