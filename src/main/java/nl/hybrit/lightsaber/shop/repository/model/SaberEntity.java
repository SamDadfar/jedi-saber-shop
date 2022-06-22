package nl.hybrit.lightsaber.shop.repository.model;

import lombok.*;
import nl.hybrit.lightsaber.shop.repository.enums.CrystalEnum;

import javax.persistence.*;

/**
 * SaberModel class
 */
@Getter
@Setter
@Entity
@Table(name = "saber")
public class SaberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer saberId;
    private String name;
    private Integer available;

    @Enumerated(EnumType.STRING)
    private CrystalEnum crystalType;

    private String crystalName;

}
