package nl.hybrit.lightsaber.shop.repository;

import nl.hybrit.lightsaber.shop.repository.model.SaberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaberRepository extends JpaRepository<SaberEntity, Long> {

    Optional<SaberEntity> findBySaberId(int saberId);
}
