package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.request.OrderRequest;
import iuh.fit.se.techgalaxy.dto.response.OrderResponse;
import iuh.fit.se.techgalaxy.entities.Order;
import iuh.fit.se.techgalaxy.mapper.OrderMapper;
import iuh.fit.se.techgalaxy.repository.OrderRepository;
import iuh.fit.se.techgalaxy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Save order
     * @param orderRequest
     * @return OrderResponse
     * author: PhamVanThanh
     */
    @Override
    public OrderResponse save(OrderRequest orderRequest) {
        Order order = orderRepository.save(OrderMapper.INSTANCE.toOrderFromRequest(orderRequest));
        return OrderMapper.INSTANCE.toOrderResponse(order);
    }

    /**
     * Find order by id
     * @param id
     * @return OrderResponse
     * author: PhamVanThanh
     */
    @Override
    public OrderResponse findById(String id) {
        Order order = orderRepository.findById(id).orElse(null);
        return OrderMapper.INSTANCE.toOrderResponse(order);
    }

    /**
     * Update order
     * @param orderRequest
     * @return OrderResponse
     * author: PhamVanThanh
     */
    @Override
    public OrderResponse update(String id, OrderRequest orderRequest) {
        if (!orderRepository.existsById(id))
            return null;
        Order order = orderRepository.save(OrderMapper.INSTANCE.toOrderFromRequest(orderRequest));
        return OrderMapper.INSTANCE.toOrderResponse(order);
    }

    /**
     * Find all orders with pagination
     * @param page
     * @param size
     * @return PagedModel<OrderResponse>
     * author: PhamVanThanh
     */
    @Override
    public PagedModel<OrderResponse> findAllOrders(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Order> orderPage = orderRepository.findAll(pageRequest);

        List<OrderResponse> orderResponses = orderPage.getContent()
                .stream()
                .map(OrderMapper.INSTANCE::toOrderResponse)
                .collect(Collectors.toList());
        return PagedModel.of(
                orderResponses,
                new PagedModel.PageMetadata(
                        orderPage.getSize(),
                        orderPage.getNumber(),
                        orderPage.getTotalElements()
                )
        );
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper.INSTANCE::toOrderResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> findOrdersByCustomerId(String id) {
        return orderRepository.getOrdersByCustomerId(id)
                .stream()
                .map(OrderMapper.INSTANCE::toOrderResponse)
                .collect(Collectors.toList());
    }
}
