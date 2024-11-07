package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.response.ProductResponse;
import iuh.fit.se.techgalaxy.mapper.ProductMapper;
import iuh.fit.se.techgalaxy.repository.ProductRepository;
import iuh.fit.se.techgalaxy.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;
    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .toList();
    }
}
