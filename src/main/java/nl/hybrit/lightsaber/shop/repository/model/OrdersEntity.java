package nl.hybrit.lightsaber.shop.repository.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(name = "order_saber",
            joinColumns = {@JoinColumn(name = "fk_order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_saber_id", referencedColumnName = "id")})
    private List<SaberEntity> sabers = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "padawan_id", referencedColumnName = "id")
    private PadawanEntity padawan;
}
