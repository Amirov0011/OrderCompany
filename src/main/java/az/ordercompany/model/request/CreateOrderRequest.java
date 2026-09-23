package az.ordercompany.model.request;

import az.ordercompany.model.constance.ApplicationConstants;
import az.ordercompany.model.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequest {


    @NotNull(message = ApplicationConstants.PRODUCT_ID_IS_REQUIRED)
    private Long productId;

    @NotNull(message = ApplicationConstants.QUANTITY_IS_REQUIRED)
    private Integer quantity;

    private PaymentType paymentType;

    private BigDecimal amount;

}
