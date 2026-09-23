package az.ordercompany.client;

import az.ordercompany.model.client.request.CreatePaymentRequest;
import az.ordercompany.model.client.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "ms-payment",
        url = "http://localhost:3333/v1/payments",
        configuration = CustomErrorDecoder.class
)

public interface PaymentClient {
    @PostMapping
    void pay(CreatePaymentRequest createPaymentRequest);

    @GetMapping("/order/{orderId}")
    PaymentResponse getPaymentByOrderId(@PathVariable Long orderId);


}
