package az.ordercompany.client;


import az.ordercompany.model.client.request.ReduceQuantityRequest;
import az.ordercompany.model.client.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name= "ProductsCompany",
        url= "http://localhost:5555/v1/products",
        configuration= CustomErrorDecoder.class
)
public interface ProductClient {
    @PostMapping("/reduce-quantity")
    void reduceQuantity(@RequestBody ReduceQuantityRequest reduceQuantityRequest);

    @GetMapping("/{id}")
    ProductResponse getProductById(@PathVariable Long id);
}
