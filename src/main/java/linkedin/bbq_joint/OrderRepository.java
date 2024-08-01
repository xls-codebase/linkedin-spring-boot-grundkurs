package linkedin.bbq_joint;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;

@Repository
public class OrderRepository {

    private final Map<UUID, Order> orders = new TreeMap<>();

    public Order save(Order order) {
        order.setId(UUID.randomUUID());
        orders.put(order.getId(), order);
        return order;
    }

    public Optional<Order> findById(UUID id) {
        if (!orders.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.ofNullable(orders.get(id));
    }

}
