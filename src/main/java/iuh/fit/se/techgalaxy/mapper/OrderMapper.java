package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.OrderRequest;
import iuh.fit.se.techgalaxy.dto.response.OrderResponse;
import iuh.fit.se.techgalaxy.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    /**
     * @param orderResponse
     * @return Order
     * author: PhamVanThanh
     */
    Order toOrderFromOrderResponse(OrderResponse orderResponse);

    /**
     * @param orderRequest
     * @return Order
     * author: PhamVanThanh
     */
    Order toOrderFromOrderRequest(OrderRequest orderRequest);

    /**
     * @param order
     * @return OrderResponse
     * author: PhamVanThanh
     */
    OrderResponse toOrderResponse(Order order);
}
