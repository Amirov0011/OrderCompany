package az.ordercompany.model.event;

public record PaymentResultEvent(Long orderId,
                                 boolean success,
                                 String message) {



}
