package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.request.ProductFeedbackRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductFeedbackResponse;

import java.util.List;

public interface ProductFeedbackService {
    ProductFeedbackResponse createFeedback(ProductFeedbackRequest productFeedbackRequest);//save

    boolean deleteFeedback(String id); //delete

    ProductFeedbackResponse updateFeedback(String id, Integer newFeedbackRating, String newFeedbackText);// save || update

    List<ProductFeedbackResponse> getAllFeedback(); //findAll

    List<ProductFeedbackResponse> getFeedbackByCustomerId(String customerId);

    List<ProductFeedbackResponse> getFeedbackByProductVariantId(String productVariantId);

}
