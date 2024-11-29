package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.request.ProductFeedbackRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductFeedbackResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductFeedbackResponseV2;
import iuh.fit.se.techgalaxy.entities.ProductFeedback;
import iuh.fit.se.techgalaxy.exception.AppException;
import iuh.fit.se.techgalaxy.exception.ErrorCode;
import iuh.fit.se.techgalaxy.mapper.ProductFeedbackMapper;
import iuh.fit.se.techgalaxy.repository.ProductFeedbackRepository;
import iuh.fit.se.techgalaxy.service.ProductFeedbackService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductFeedbackServiceImpl implements ProductFeedbackService {
    ProductFeedbackRepository productFeedbackRepository;
    ProductFeedbackMapper productFeedbackMapper;

    @Override
    public ProductFeedbackResponse createFeedback(ProductFeedbackRequest productFeedbackRequest) {
        // TODO Auto-generated method stub
        //.... xu ly doi

        ProductFeedback productFeedback = productFeedbackMapper.toEntity(productFeedbackRequest);
        //

        ProductFeedback productFeedbackCreate = productFeedbackRepository.save(productFeedback);
        //save kh co id thi se la inssert
        //save ma co id thi se la update

        ProductFeedbackResponse productFeedbackResponse = productFeedbackMapper.toResponsedto(productFeedbackCreate);
        //
        return productFeedbackResponse;
    }

    @Override
    public boolean deleteFeedback(String id) {

        ProductFeedback productFeedack = productFeedbackRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.FEEDBACK_NOTFOUND));
        if (productFeedack != null) {
            productFeedbackRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ProductFeedbackResponse updateFeedback(String id, Integer newFeedbackRating, String newFeedbackText) {
        ProductFeedback productFeedack = productFeedbackRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.FEEDBACK_NOTFOUND));
        if (productFeedack != null) {
            productFeedack.setFeedbackRating(null);
            productFeedack.setFeedbackText(newFeedbackText);

            return productFeedbackMapper.toResponsedto(productFeedbackRepository.save(productFeedack));
        }
        return null;
    }

    @Override
    public List<ProductFeedbackResponse> getAllFeedback() {
        List<ProductFeedback> feedbacks = productFeedbackRepository.findAll();
        return feedbacks.stream()
                .map(productFeedbackMapper::toResponsedto)
                .collect(Collectors.toList());
    }


    @Override
    public List<ProductFeedbackResponse> getFeedbackByCustomerId(String customerId) {
        List<ProductFeedback> feedbacks = productFeedbackRepository.findByCustomerId(customerId);
        return feedbacks.stream().map(productFeedbackMapper::toResponsedto).collect(Collectors.toList());
    }

    @Override
    public List<ProductFeedbackResponse> getFeedbackByProductVariantId(String productVariantId) {
        List<ProductFeedback> feedbacks = productFeedbackRepository.findByProductVariantId(productVariantId);
        return feedbacks.stream()
                .map(productFeedbackMapper::toResponsedto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductFeedbackResponseV2> getFeedbackByProductVariantIdV2(String productVariantId) {
        List<ProductFeedback> feedbacks = productFeedbackRepository.findByProductVariantId(productVariantId);
        return feedbacks.stream()
                .map(productFeedbackMapper::toProductFeedbackResponseV2)
                .collect(Collectors.toList());
    }
}
