package az.ordercompany.controller;

import az.ordercompany.model.request.CreateOrderRequest;
import az.ordercompany.model.response.OrderResponse;
import az.ordercompany.service.abstraction.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.ServiceUnavailableException;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        orderService.createOrder(createOrderRequest);
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "getOrderById", fallbackMethod = "fallback")
    public OrderResponse getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    public OrderResponse fallback(Long response,Exception exception) throws ServiceUnavailableException {
        throw new ServiceUnavailableException("Service is currently unavailable.");
    }
}

