package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.OrderDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.OrderDetailResponse;
import iuh.fit.se.techgalaxy.entities.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

    @Mapping(target = "productVariantDetail.name", source = "orderDetail.productVariantDetail.productVariant.name")
    OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail);

    OrderDetailRequest toOrderDetailRequest(OrderDetail orderDetail);

    OrderDetail toOrderDetailFromRequest(OrderDetailRequest orderDetailRequest);

    OrderDetail toOrderDetailFromResponse(OrderDetailResponse orderDetailResponse);
}
