package az.ordercompany.mapper;

import az.ordercompany.dao.entity.OrderEntity;
import az.ordercompany.model.client.response.PaymentResponse;
import az.ordercompany.model.client.response.ProductResponse;
import az.ordercompany.model.enums.OrderStatus;
import az.ordercompany.model.request.CreateOrderRequest;
import az.ordercompany.model.response.OrderResponse;


import java.time.LocalDateTime;

public enum OrderMapper {
    ORDER_MAPPER;

    public OrderEntity buildOrderEntity(CreateOrderRequest createOrderRequest) {
        return OrderEntity.builder()
                .productId(createOrderRequest.getProductId())
                .quantity(createOrderRequest.getQuantity())
                .amount(createOrderRequest.getAmount())
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

    }

    public OrderResponse buildOrderResponse(OrderEntity orderEntity,
                                            ProductResponse productResponse,
                                            PaymentResponse paymentResponse) {
        return  OrderResponse.builder()
                .id(orderEntity.getId())
                .quantity(orderEntity.getQuantity())
                .productId(orderEntity.getProductId())
                .amount(orderEntity.getAmount())
                .status(orderEntity.getStatus())
                .createdAt(orderEntity.getCreatedAt())
                .product(productResponse)
                .paymentResponse(paymentResponse)
                .build();
    }


}
