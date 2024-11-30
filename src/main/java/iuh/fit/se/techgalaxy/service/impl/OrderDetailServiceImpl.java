package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.request.OrderDetailRequest;
import iuh.fit.se.techgalaxy.dto.response.OrderDetailResponse;
import iuh.fit.se.techgalaxy.entities.OrderDetail;
import iuh.fit.se.techgalaxy.entities.ProductVariantDetail;
import iuh.fit.se.techgalaxy.exception.AppException;
import iuh.fit.se.techgalaxy.exception.ErrorCode;
import iuh.fit.se.techgalaxy.mapper.OrderDetailMapper;
import iuh.fit.se.techgalaxy.mapper.ProductMapper;
import iuh.fit.se.techgalaxy.mapper.ProductVariantDetailMapper;
import iuh.fit.se.techgalaxy.repository.OrderDetailRepository;
import iuh.fit.se.techgalaxy.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ProductMapper productMapper;
    private final ProductVariantDetailMapper productVariantDetailMapper;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ProductMapper productMapper, ProductVariantDetailMapper productVariantDetailMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.productMapper = productMapper;
        this.productVariantDetailMapper = productVariantDetailMapper;
    }

    /**
     * Get order details by order id
     *
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
     *
     * @param orderDetailRequest
     * @return OrderDetailResponse
     * author: PhamVanThanh
     */
    @Override
    public OrderDetailResponse save(OrderDetailRequest orderDetailRequest) {
        OrderDetail orderDetail = orderDetailRepository.save(OrderDetailMapper.INSTANCE.toOrderDetailFromRequest(orderDetailRequest));
        return OrderDetailMapper.INSTANCE.toOrderDetailResponse(orderDetail);
    }


    /**
     * Find order detail by id
     *
     * @param id
     * @return OrderDetailResponse
     * author: PhamVanThanh
     */
    @Override
    public OrderDetailResponse findById(String id) {
        return OrderDetailMapper.INSTANCE.toOrderDetailResponse(orderDetailRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOTFOUND)));
    }

    @Override
    public OrderDetailResponse update(String id, OrderDetailRequest orderDetailRequest) {
        return orderDetailRepository.findById(id)
                .map(orderDetail -> {
                    ProductVariantDetail productVariantDetail = new ProductVariantDetail();
                    productVariantDetail.setId(orderDetailRequest.getProductVariantDetail().getId());
                    orderDetail.setQuantity(orderDetailRequest.getQuantity());
                    orderDetail.setProductVariantDetail(productVariantDetail);
                    return OrderDetailMapper.INSTANCE.toOrderDetailResponse(orderDetailRepository.save(orderDetail));
                })
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOTFOUND));
    }
}
