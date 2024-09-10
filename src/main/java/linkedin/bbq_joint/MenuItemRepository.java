package linkedin.bbq_joint;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, UUID> {
    Iterable<MenuItem> findByOrderByDrinkDescNameDesc();

    Iterable<MenuItem> findByDrinkOrderByNameDesc(boolean drink);

}
