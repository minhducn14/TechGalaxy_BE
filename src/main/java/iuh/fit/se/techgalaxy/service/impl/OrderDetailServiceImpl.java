package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.request.OrderDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.OrderDetailResponse;
import iuh.fit.se.techgalaxy.entities.OrderDetail;
import iuh.fit.se.techgalaxy.mapper.OrderDetailMapper;
import iuh.fit.se.techgalaxy.repository.OrderDetailRepository;
import iuh.fit.se.techgalaxy.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    /**
     * Get order details by order id
     * @param orderId
     * @return List<OrderDetailResponse>
     * author: PhamVanThanh
     */
    @Override
    public List<OrderDetailResponse> getOrderDetailsByOrderId(String orderId) {
        List<OrderDetail> orderDetails = this.orderDetailRepository.findOrderDetailsByOrderId(orderId);
        return orderDetails.stream()
                .map(OrderDetailMapper.INSTANCE::toOrderDetailResponse)
                .collect(Collectors.toList());
    }

    /**
     * Save order detail
     * @param orderDetailRequest
     * @return OrderDetailResponse
     * author: PhamVanThanh
     */
    @Override
    public OrderDetailResponse save(OrderDetailRequest orderDetailRequest) {
        if (orderDetailRequest.getId() == null || orderDetailRequest.getOrder().getId() == null || orderDetailRequest.getProductVariantDetail().getId() == null)
            return null;
        OrderDetail orderDetail = orderDetailRepository.save(OrderDetailMapper.INSTANCE.toOrderDetailFromRequest(orderDetailRequest));
        return OrderDetailMapper.INSTANCE.toOrderDetailResponse(orderDetail);
    }
}
