package nl.hybrit.lightsaber.shop.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static nl.hybrit.lightsaber.shop.services.impl.PadawanServiceImpl.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "padawan")
public class PadawanEntity {

    @Id
    private Long id;

    private Integer force;
    private Boolean jedi;
    private Integer age;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "padawan_saber",
            joinColumns = {@JoinColumn(name = "fk_padawan_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_saber_id", referencedColumnName = "id")})
    private List<SaberEntity> sabers;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private UserEntity user;

    public PadawanEntity(Integer age) {
        this.age = age;
        this.force = getForce();
        this.jedi = this.isAdult();
    }

    public Integer getForce() {
        return this.age * 10;
    }

    public boolean isAdult() {
        return this.age > ADULT_AGE;
    }

    public boolean isJedi() {
        return this.force > ENOUGH_FORCE_AS_JEDI;
    }

    public boolean isDissolve() {
        return this.getAge() >= JEDI_DISSOLVE_CONSTANT;
    }

}
