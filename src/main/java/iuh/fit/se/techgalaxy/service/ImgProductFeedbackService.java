package iuh.fit.se.techgalaxy.service;

import java.util.List;

import iuh.fit.se.techgalaxy.dto.request.ImgProductFeedbackRequest;
import iuh.fit.se.techgalaxy.dto.response.ImgProductFeedbackResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductFeedbackResponse;
import iuh.fit.se.techgalaxy.entities.ImgProductFeedback;

public interface ImgProductFeedbackService {
	ImgProductFeedbackResponse createImgFeedback(String productFeedbackId, ImgProductFeedbackRequest imgProductFeedbackRequest);
	boolean deleteImgFeedback(String id); 
	ImgProductFeedbackResponse updateImgFeedback(String id, String newImgPath);
	String getImgPath(String id);
}
