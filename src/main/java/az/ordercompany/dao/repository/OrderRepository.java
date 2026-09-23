package az.ordercompany.dao.repository;

import az.ordercompany.dao.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
