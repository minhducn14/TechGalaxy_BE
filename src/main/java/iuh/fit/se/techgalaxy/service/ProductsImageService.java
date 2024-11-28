package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.request.ProductsImageRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductsImageResponse;

import java.util.List;

public interface ProductsImageService {

    List<ProductsImageResponse> createProductsImage(String variantDetail, List<ProductsImageRequest> productsImageRequest);

    List<ProductsImageResponse> getProductsImageByProductId(String productId);

    void deleteProductsImageByProductId(String productId);

    void deleteProductsImageByImageId(String imageId);
}
