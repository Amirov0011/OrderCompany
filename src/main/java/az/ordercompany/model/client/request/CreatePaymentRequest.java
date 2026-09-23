package az.ordercompany.model.client.request;

import az.ordercompany.model.client.response.ProductResponse;
import az.ordercompany.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class CreatePaymentRequest {

    private Long orderId;

    private PaymentType paymentType;

    private BigDecimal amount;

    private Integer quantity;

    private  String referenceNumber;

    private ProductResponse product;

}
