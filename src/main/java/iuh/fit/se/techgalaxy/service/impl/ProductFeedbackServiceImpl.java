package iuh.fit.se.techgalaxy.service.impl;

import java.util.List;

import iuh.fit.se.techgalaxy.dto.response.ProductFeedbackResponse;
import iuh.fit.se.techgalaxy.repository.ProductFeedbackRepository;
import iuh.fit.se.techgalaxy.service.ProductFeedbackService;

public class ProductFeedbackServiceImpl implements ProductFeedbackService {
	ProductFeedbackRepository productFeedbackRepository;
	
	@Override
	public ProductFeedbackResponse createFeedback(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteFeedback(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProductFeedbackResponse updateTrademark(String id, String feedbackText) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductFeedbackResponse> getAllFeedback() {
		// TODO Auto-generated method stub
		return null;
	}

}
