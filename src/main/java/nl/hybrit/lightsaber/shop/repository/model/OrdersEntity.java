package nl.hybrit.lightsaber.shop.repository.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Order Class
 */
@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "order_saber",
            joinColumns = {@JoinColumn(name = "fk_order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_saber_id", referencedColumnName = "id")})
    private List<SaberEntity> sabers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "padawan_id", referencedColumnName = "id")
    private PadawanEntity padawan;
}
