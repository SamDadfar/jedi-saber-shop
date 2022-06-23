package nl.hybrit.lightsaber.shop.repository;

import nl.hybrit.lightsaber.shop.repository.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Order Repository
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
