package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.ProductsImageRequest;
import iuh.fit.se.techgalaxy.dto.response.ProductsImageResponse;
import iuh.fit.se.techgalaxy.entities.ProductsImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductsImageMapper {

    ProductsImage toProdcutsImage(ProductsImageRequest productsImageRequest);


    ProductsImageResponse toProductsImageResponse(ProductsImage productsImage);
}
