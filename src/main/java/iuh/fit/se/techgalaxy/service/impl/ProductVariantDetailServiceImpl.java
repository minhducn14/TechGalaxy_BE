package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.request.ProductVariantDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantDetailResponse;
import iuh.fit.se.techgalaxy.entities.Color;
import iuh.fit.se.techgalaxy.entities.Memory;
import iuh.fit.se.techgalaxy.entities.ProductVariant;
import iuh.fit.se.techgalaxy.entities.ProductVariantDetail;
import iuh.fit.se.techgalaxy.mapper.ProductVariantDetailMapper;
import iuh.fit.se.techgalaxy.repository.ColorRepository;
import iuh.fit.se.techgalaxy.repository.MemoryRepository;
import iuh.fit.se.techgalaxy.repository.ProductVariantDetailRepository;
import iuh.fit.se.techgalaxy.repository.ProductVariantRepository;
import iuh.fit.se.techgalaxy.service.ProductVariantDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductVariantDetailServiceImpl implements ProductVariantDetailService {
    ProductVariantDetailRepository productVariantDetailRepository;
    ProductVariantRepository productVariantRepository;
    ProductVariantDetailMapper productVariantDetailMapper;
    ColorRepository colorRepository;
    MemoryRepository memoryRepository;

    @Override
    public ProductVariantDetailResponse getProductVariantDetail(String variantId) {  // Lấy tất cả các ProductVariantDetail theo productVariantId
        List<ProductVariantDetail> details = productVariantDetailRepository.findAllByProductVariantId(variantId);
        if (details.isEmpty()) {
            throw new RuntimeException("Product variant detail not found");
        }
        return productVariantDetailMapper.toProductVariantDetailResponse(details.get(0), details);
    }

    @Override
    public Boolean createProductVariantDetail(String variantId, ProductVariantDetailRequest productVariantDetailRequest) {
        ProductVariant productVariant = productVariantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Product variant not found"));
        Set<String> colorIds = productVariantDetailRequest.getMemoryColorRequests().stream()
                .map(ProductVariantDetailRequest.MemoryColorRequest::getColorId)
                .collect(Collectors.toSet());
        Set<String> memoryIds = productVariantDetailRequest.getMemoryColorRequests().stream()
                .map(ProductVariantDetailRequest.MemoryColorRequest::getMemoryId)
                .collect(Collectors.toSet());

        Map<String, Color> colorMap = colorRepository.findAllById(colorIds)
                .stream().collect(Collectors.toMap(Color::getId, color -> color));
        Map<String, Memory> memoryMap = memoryRepository.findAllById(memoryIds)
                .stream().collect(Collectors.toMap(Memory::getId, memory -> memory));
        List<ProductVariantDetail> productVariantDetails = productVariantDetailRequest.getMemoryColorRequests().stream().map(detail -> {
            Color color = colorMap.get(detail.getColorId());
            Memory memory = memoryMap.get(detail.getMemoryId());
            if (color == null) {
                throw new RuntimeException("Color not found: " + detail.getColorId());
            }
            if (memory == null) {
                throw new RuntimeException("Memory not found: " + detail.getMemoryId());
            }
            return productVariantDetailMapper.toProductVariantDetail(productVariantDetailRequest, detail, color, memory, productVariant);
        }).toList();
        productVariantDetailRepository.saveAll(productVariantDetails);
        return true;
    }


}
