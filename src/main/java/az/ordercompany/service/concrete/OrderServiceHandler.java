package az.ordercompany.service.concrete;


import az.ordercompany.dao.repository.OrderRepository;

import az.ordercompany.model.enums.OrderStatus;
import az.ordercompany.model.event.OrderCreatedEvent;
import az.ordercompany.model.request.CreateOrderRequest;
import az.ordercompany.model.response.OrderResponse;
import az.ordercompany.service.OrderKafkaProducer;
import az.ordercompany.service.abstraction.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import static az.ordercompany.mapper.OrderMapper.ORDER_MAPPER;



@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceHandler implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderKafkaProducer kafkaProducer;

    @Override
    @Transactional
    public void createOrder(CreateOrderRequest createOrderRequest) {

        var orderEntity = ORDER_MAPPER.buildOrderEntity(createOrderRequest);
        orderEntity.setStatus(OrderStatus.PENDING);


        var savedOrder = orderRepository.save(orderEntity);


        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId(),
                createOrderRequest.getProductId(),
                createOrderRequest.getQuantity(),
                savedOrder.getAmount(),
                createOrderRequest.getPaymentType()


        );


        kafkaProducer.sendOrderCreatedEvent(event);

        log.info("Sifariş PENDING statusunda yaradıldı və Kafka-ya göndərildi. Order ID: {}", savedOrder.getId());
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return null;
    }




      /*  var orderEntity = ORDER_MAPPER.buildOrderEntity(createOrderRequest);

        var productResponse =
                productClient.getProductById(createOrderRequest.getProductId());

        var totalAmount = productResponse.getPrice().multiply(valueOf(orderEntity.getQuantity()));
        orderEntity.setAmount(totalAmount);

        var reduceQuantityRequest = new ReduceQuantityRequest(
                createOrderRequest.getProductId(),
                createOrderRequest.getQuantity());

        orderRepository.save(orderEntity);
        productClient.reduceQuantity(reduceQuantityRequest);

        try {
            paymentClient.pay(PaymentMapper.PAYMENT_MAPPER.buildCreatePaymentRequest(
                    createOrderRequest,
                    orderEntity,
                    totalAmount));
            orderEntity.setStatus(OrderStatus.APPROVED);
        } catch (Exception e) {
            log.error("Payment error: ", e);

            orderEntity.setStatus(OrderStatus.REJECTED);
        }
        orderRepository.save(orderEntity);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        var orderEntity = orderRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                (format(ORDER_NOT_FOUND.getMessage(),
                                        id
                                ))));

        var productResponse = productClient.getProductById(orderEntity.getProductId());
        var paymentResponse = paymentClient.getPaymentByOrderId(orderEntity.getId());
        return ORDER_MAPPER.buildOrderResponse(orderEntity,
                productResponse,
                paymentResponse);
    }  */

    }
