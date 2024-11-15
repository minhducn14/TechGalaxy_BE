package iuh.fit.se.techgalaxy.service;

import java.util.List;

import iuh.fit.se.techgalaxy.dto.response.ProductFeedbackResponse;

public interface ProductFeedbackService {
    ProductFeedbackResponse createFeedback(String id);
    boolean deleteFeedback(String id);
    ProductFeedbackResponse updateTrademark(String id, String feedbackText);
    List<ProductFeedbackResponse> getAllFeedback();
}
