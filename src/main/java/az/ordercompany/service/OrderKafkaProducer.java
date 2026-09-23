package az.ordercompany.service;


import az.ordercompany.model.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Kafka-ya event atılır: Order ID {}", event.orderId());
        kafkaTemplate.send("order-created-topic", String.valueOf(event.orderId()), event);
    }
}
