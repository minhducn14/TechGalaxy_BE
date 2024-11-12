package iuh.fit.se.techgalaxy.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import iuh.fit.se.techgalaxy.dto.request.AttributeRequest;
import iuh.fit.se.techgalaxy.dto.response.AttributeResponse;
import iuh.fit.se.techgalaxy.entities.Attribute;
import iuh.fit.se.techgalaxy.mapper.AttributeMapper;
import iuh.fit.se.techgalaxy.repository.AttributeRepository;
import iuh.fit.se.techgalaxy.service.AttributeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public  class AttributeServiceImpl implements AttributeService {

	AttributeMapper attributeMapper;
	AttributeRepository attributeRepository;

	@Override
	public AttributeResponse createAttribute(AttributeRequest attributeRequest) {
		Attribute attribute = attributeMapper.toAttribute(attributeRequest);
		return attributeMapper.toAttributeResponse(attributeRepository.save(attribute));

	}

	@Transactional
	@Override
	public AttributeResponse updateAttribute(String id, AttributeRequest attributeRequest) {
	    Attribute attribute = attributeRepository.findById(id)
	             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attribute not found"));
	    attributeMapper.updateAttributeFromRequest(attribute, attributeRequest);
	    return attributeMapper.toAttributeResponse(attributeRepository.save(attribute));
	}


	@Override
	public AttributeResponse deleteAttribute(String id) {
		Attribute attribute = attributeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Attribute not found"));
		return attributeMapper.toAttributeResponse(attribute);
	}

	@Override
	public AttributeResponse getAttributeById(String id) {
		
		return attributeRepository.findById(id)
                .map(attributeMapper::toAttributeResponse)
                .orElseThrow(() -> new RuntimeException("Attribute not found"));
	}

	@Override
	public Set<AttributeResponse> getAllAttribute() {
		 return attributeRepository.findAll().stream()
	                .map(attributeMapper::toAttributeResponse)
	                .collect(Collectors.toSet());
	}

}
