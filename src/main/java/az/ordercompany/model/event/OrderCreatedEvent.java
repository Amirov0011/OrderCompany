package az.ordercompany.model.event;

import az.ordercompany.model.enums.PaymentType;

import java.math.BigDecimal;

public record OrderCreatedEvent(Long orderId,
                                Long productId,
                                Integer quantity,
                                BigDecimal amount,
                                PaymentType paymentType
) {

}
