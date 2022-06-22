package nl.hybrit.lightsaber.shop.repository;

import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PadawanRepository extends JpaRepository<PadawanEntity, Long> {
}
