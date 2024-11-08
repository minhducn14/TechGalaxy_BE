package iuh.fit.se.techgalaxy.service;

import java.util.Set;

import iuh.fit.se.techgalaxy.dto.request.AttributeRequest;
import iuh.fit.se.techgalaxy.dto.response.AttributeRespone;

public interface AttributeService {

	AttributeRespone createAttribute(AttributeRequest attributeRequest);

	AttributeRespone updateAttribute(String id, AttributeRequest attributeRequest);

	AttributeRespone deleteAttribute(String id, AttributeRequest attributeRequest);

	AttributeRespone getAttributeById(String id);
	
	Set<AttributeRespone> getAllAttribute();
}
