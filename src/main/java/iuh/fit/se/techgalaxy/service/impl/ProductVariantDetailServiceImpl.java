package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.request.ProductDetailUpdateRequest;
import iuh.fit.se.techgalaxy.dto.request.ProductVariantDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductDetailResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductPageResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantDetailResponse;
import iuh.fit.se.techgalaxy.entities.Color;
import iuh.fit.se.techgalaxy.entities.Memory;
import iuh.fit.se.techgalaxy.entities.ProductVariant;
import iuh.fit.se.techgalaxy.entities.ProductVariantDetail;
import iuh.fit.se.techgalaxy.exception.AppException;
import iuh.fit.se.techgalaxy.exception.ErrorCode;
import iuh.fit.se.techgalaxy.mapper.ProductVariantDetailMapper;
import iuh.fit.se.techgalaxy.repository.ColorRepository;
import iuh.fit.se.techgalaxy.repository.MemoryRepository;
import iuh.fit.se.techgalaxy.repository.ProductVariantDetailRepository;
import iuh.fit.se.techgalaxy.repository.ProductVariantRepository;
import iuh.fit.se.techgalaxy.service.ProductVariantDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductVariantDetailServiceImpl implements ProductVariantDetailService {
    ProductVariantDetailRepository productVariantDetailRepository;
    ProductVariantRepository productVariantRepository;
    ProductVariantDetailMapper productVariantDetailMapper;
    ColorRepository colorRepository;
    MemoryRepository memoryRepository;

    @Override
    public ProductVariantDetailResponse getProductVariantDetail(String variantId) {
        List<ProductVariantDetail> details = productVariantDetailRepository.findAllByProductVariantId(variantId);
        if (details.isEmpty()) {
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
        }
        return productVariantDetailMapper.toProductVariantDetailResponse(details.get(0), details);
    }

    @Override
    public ProductDetailResponse getProductDetail(String productDetailId) {
        ProductVariantDetail productVariantDetail = productVariantDetailRepository.findById(productDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOTFOUND));
        return productVariantDetailMapper.toResponse(productVariantDetail);
    }

    @Override
    public Boolean createProductVariantDetail(String variantId, List<ProductVariantDetailRequest> productVariantDetailRequests) {
        ProductVariant productVariant = productVariantRepository.findById(variantId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOTFOUND));
        List<ProductVariantDetail> detailsToSave = new ArrayList<>();
        for (ProductVariantDetailRequest requestDTO : productVariantDetailRequests) {
            Memory memory = memoryRepository.findById(requestDTO.getMemid())
                    .orElseThrow(() -> new AppException(ErrorCode.MEMORY_NOTFOUND));

            for (ProductVariantDetailRequest.ColorRequest colorRequest : requestDTO.getColors()) {
                Color color = colorRepository.findById(colorRequest.getColorId())
                        .orElseThrow(() -> new AppException(ErrorCode.COLOR_NOTFOUND));
                ProductVariantDetail detail = productVariantDetailMapper.toProductVariantDetail(requestDTO, colorRequest, color, memory, productVariant);
                detailsToSave.add(detail);
            }
        }
        productVariantDetailRepository.saveAll(detailsToSave);
        return true;
    }

    @Override
    public Boolean updateProductVariantDetail(String productDetailId, ProductDetailUpdateRequest productDetailUpdateRequest) {
        boolean state = true;
        ProductVariantDetail productVariantDetail = productVariantDetailRepository.findById(productDetailId).orElseThrow(() ->
                new AppException(ErrorCode.PRODUCT_NOTFOUND));
        productVariantDetailMapper.toUpdate(productVariantDetail, productDetailUpdateRequest);
        try {
            productVariantDetailRepository.save(productVariantDetail);
        } catch (Exception e) {
            throw new AppException(ErrorCode.PRODUCT_UPDATE_FAILED);
        }
        return state;
    }

    @Override
    public void deleteProductVariantDetail(String productDetailId) {
        try {
            productVariantDetailRepository.deleteById(productDetailId);
        } catch (Exception e) {
            throw new AppException(ErrorCode.PRODUCT_DELETE_FAILED);
        }
    }

    @Override
    public Page<ProductPageResponse> getFilteredProductDetails(List<String> trademark, Double minPrice, Double maxPrice, List<String> memory, List<String> usageCategoryId, List<String> values, String sort, Integer page, Integer size) {
        Pageable pageable;
        if (sort != null && !sort.isEmpty()) {
            Sort sortOrder = sort.equalsIgnoreCase("asc") ? Sort.by("price").ascending() : Sort.by("price").descending();
            pageable = PageRequest.of(page, size, sortOrder);
        } else {
            pageable = PageRequest.of(page, size);
        }
        Page<ProductVariantDetail> productVariantDetails = productVariantDetailRepository.findFilteredProductDetails(trademark, minPrice, maxPrice, memory, usageCategoryId, values, pageable);
        return productVariantDetails.map(productVariantDetailMapper::toResponsePage);
    }

    @Override
    public ProductDetailResponse findProductVariantDetailByProductVariantAndColorAndMemory(String productVariantId, String color, String memory) {
        ProductVariantDetail productVariantDetail = productVariantDetailRepository.findProductVariantDetailByProductVariantAndColorAndMemory(productVariantId, color, memory);
        return productVariantDetailMapper.toResponse(productVariantDetail);
    }
}
