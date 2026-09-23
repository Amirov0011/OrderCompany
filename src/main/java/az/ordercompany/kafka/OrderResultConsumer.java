package az.ordercompany.kafka;


import az.ordercompany.dao.repository.OrderRepository;
import az.ordercompany.model.enums.OrderStatus;
import az.ordercompany.model.event.PaymentResultEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderResultConsumer {

    private final OrderRepository orderRepository;

    @Transactional
    @KafkaListener(topics = "payment-result-topic", groupId = "order-group")
    public void handlePaymentResult(PaymentResultEvent event) {
        log.info("Ödəniş nəticəsi qəbul edildi: Order ID: {}, Uğurlu: {}", event.orderId(), event.success());

        orderRepository.findById(event.orderId()).ifPresentOrElse(order -> {
            if (event.success()) {
                order.setStatus(OrderStatus.APPROVED);
            } else {
                order.setStatus(OrderStatus.REJECTED);
            }
            orderRepository.save(order);
            log.info("Order status yeniləndi: {}", order.getStatus());
        }, () -> log.error("Order tapılmadı: ID {}", event.orderId()));
    }
}
