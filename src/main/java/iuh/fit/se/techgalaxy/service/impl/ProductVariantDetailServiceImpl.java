package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.response.ProductVariantDetailResponse;
import iuh.fit.se.techgalaxy.entities.ProductVariantDetail;
import iuh.fit.se.techgalaxy.mapper.ProductVariantDetailMapper;
import iuh.fit.se.techgalaxy.repository.ProductVariantDetailRepository;
import iuh.fit.se.techgalaxy.service.ProductVariantDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductVariantDetailServiceImpl implements ProductVariantDetailService {
    ProductVariantDetailRepository productVariantDetailRepository;

    ProductVariantDetailMapper productVariantDetailMapper;

    @Override
    public ProductVariantDetailResponse getProductVariantDetail(String variantId) {  // Lấy tất cả các ProductVariantDetail theo productVariantId
        List<ProductVariantDetail> details = productVariantDetailRepository.findAllByProductVariantId(variantId);
        if (details.isEmpty()) {
            throw new RuntimeException("Product variant detail not found");
        }
        return productVariantDetailMapper.toProductVariantDetailResponse(details.get(0), details);
    }

}
