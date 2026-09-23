package az.ordercompany.model.response;

import az.ordercompany.model.client.response.PaymentResponse;
import az.ordercompany.model.client.response.ProductResponse;
import az.ordercompany.model.enums.OrderStatus;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private Long id;

    private Long productId;

    private Integer quantity;

    private OrderStatus status;

    private BigDecimal amount;

    private LocalDateTime createdAt;

    private ProductResponse product;

    private PaymentResponse paymentResponse;


}
