package iuh.fit.se.techgalaxy.mapper;

import org.mapstruct.Mapper;

import iuh.fit.se.techgalaxy.dto.request.ProductFeedbackRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductFeedbackResponse;
import iuh.fit.se.techgalaxy.entities.ProductFeedback;

@Mapper(componentModel = "spring")
public interface ProductFeedbackMapper {
	ProductFeedbackResponse toResponsedto(ProductFeedback productFeedback);
	
	ProductFeedback toEntity(ProductFeedbackRequest feedbackRequest);
} 
