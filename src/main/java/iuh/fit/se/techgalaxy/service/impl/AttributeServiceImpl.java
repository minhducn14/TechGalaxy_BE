package iuh.fit.se.techgalaxy.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import iuh.fit.se.techgalaxy.dto.request.AttributeRequest;
import iuh.fit.se.techgalaxy.dto.response.AttributeRespone;
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
public class AttributeServiceImpl implements AttributeService {

	AttributeMapper attributeMapper;
	AttributeRepository attributeRepository;

	@Override
	public AttributeRespone createAttribute(AttributeRequest attributeRequest) {
		Attribute attribute = attributeMapper.toAttribute(attributeRequest);
		return attributeMapper.toAttributeRespone(attributeRepository.save(attribute));

	}

	@Override
	public AttributeRespone updateAttribute(String id, AttributeRequest attributeRequest) {
		Attribute attribute = attributeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Attribute not found"));
		attributeMapper.toAttribute(attributeRequest);
		return attributeMapper.toAttributeRespone(attribute);
	}

	@Override
	public AttributeRespone deleteAttribute(String id, AttributeRequest attributeRequest) {
		Attribute attribute = attributeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Attribute not found"));
		attributeMapper.toAttribute(attributeRequest);
		return attributeMapper.toAttributeRespone(attribute);
	}

	@Override
	public AttributeRespone getAttributeById(String id) {
		
		return attributeRepository.findById(id)
                .map(attributeMapper::toAttributeRespone)
                .orElseThrow(() -> new RuntimeException("Product not found"));
	}

	@Override
	public Set<AttributeRespone> getAllAttribute() {
		 return attributeRepository.findAll().stream()
	                .map(attributeMapper::toAttributeRespone)
	                .collect(Collectors.toSet());
	}

}
