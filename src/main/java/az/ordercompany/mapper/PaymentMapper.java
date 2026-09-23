package az.ordercompany.mapper;

import az.ordercompany.dao.entity.OrderEntity;
import az.ordercompany.model.client.request.CreatePaymentRequest;
import az.ordercompany.model.request.CreateOrderRequest;

import java.math.BigDecimal;

import static java.util.UUID.randomUUID;

public enum PaymentMapper {

    PAYMENT_MAPPER;


    public CreatePaymentRequest buildCreatePaymentRequest(CreateOrderRequest createOrderRequest,
                                                          OrderEntity orderEntity,
                                                          BigDecimal totalAmount) {
        return CreatePaymentRequest.builder()
                .orderId(orderEntity.getId())
                .paymentType(createOrderRequest.getPaymentType())
                .amount(totalAmount)
                .quantity(createOrderRequest.getQuantity())
                .referenceNumber(randomUUID().toString())
                .build();
    }


}
