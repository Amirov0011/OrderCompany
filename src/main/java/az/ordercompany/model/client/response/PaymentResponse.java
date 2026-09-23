package az.ordercompany.model.client.response;

import az.ordercompany.model.enums.PaymentStatus;
import az.ordercompany.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PaymentResponse {
    private Long id;

    private PaymentStatus status;

    private LocalDateTime createdAt;

    private PaymentType paymentType;


}
