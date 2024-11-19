package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.request.ImgProductFeedbackRequest;
import iuh.fit.se.techgalaxy.dto.response.ImgProductFeedbackResponse;

public interface ImgProductFeedbackService {
	ImgProductFeedbackResponse createImgFeedback(String productFeedbackId, ImgProductFeedbackRequest imgProductFeedbackRequest);
	boolean deleteImgFeedback(String id); 
	ImgProductFeedbackResponse updateImgFeedback(String id, String newImgPath);
}
