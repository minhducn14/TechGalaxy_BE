package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.ProductFeedbackRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductFeedbackResponse;
import iuh.fit.se.techgalaxy.entities.ProductFeedback;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductFeedbackMapper {
    ProductFeedbackResponse toResponsedto(ProductFeedback productFeedback);

    ProductFeedback toEntity(ProductFeedbackRequest feedbackRequest);
} 
