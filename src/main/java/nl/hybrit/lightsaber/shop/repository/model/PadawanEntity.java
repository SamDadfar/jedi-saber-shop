package nl.hybrit.lightsaber.shop.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "padawan")
public class PadawanEntity {

    @Id
    private Long id;

    @Column(name = "`force`")
    private Integer force;
    private Boolean jedi;
    @Column(name = "dob")
    private LocalDate dateOfBirth;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(name = "padawan_saber",
            joinColumns = {@JoinColumn(name = "fk_padawan_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_saber_id", referencedColumnName = "id")})
    private List<SaberEntity> sabers;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private UserEntity user;

    public final static float ENOUGH_FORCE_AS_JEDI = 93.2f;
    public final static int ADULT_AGE = 18;
    public final static int JEDI_DISSOLVE_CONSTANT = 140;

    @Default
    public PadawanEntity(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.force = getForce();
        this.jedi = isJedi();
    }

    @JsonIgnore
    public Integer getAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(this.dateOfBirth, currentDate).getYears();
    }

    public Integer getForce() {
        return getAge() * 10;
    }

    public Boolean hasUnlimitedForce() {
        return getAge() > ADULT_AGE;
    }

    public Boolean isAdult() {
        return getAge() > 18;
    }

    public Boolean isJedi() {
        return this.force > ENOUGH_FORCE_AS_JEDI;
    }

    public Boolean isDissolve() {
        return getAge() >= JEDI_DISSOLVE_CONSTANT;
    }

}
