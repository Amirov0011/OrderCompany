package az.ordercompany.service.abstraction;

import az.ordercompany.model.request.CreateOrderRequest;
import az.ordercompany.model.response.OrderResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


public interface OrderService {


    @Transactional
    void createOrder(CreateOrderRequest createOrderRequest);

    OrderResponse getOrderById(Long id);
}
