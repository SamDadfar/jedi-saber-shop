package nl.hybrit.lightsaber.shop.mapper;

import nl.hybrit.lightsaber.shop.controller.model.PadawanUserModel;
import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.Period;

@Mapper(componentModel = "spring", imports = {BCryptPasswordEncoder.class})
public interface PadawanMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "age", source = "model.dateOfBirth")
    @Mapping(target = "user.username", source = "model.username")
    @Mapping(target = "user.email", source = "model.email")
    @Mapping(target = "user.password", source = "model.password")
    PadawanEntity map(PadawanUserModel model);

    default Integer toAge(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirth, currentDate).getYears();
    }

    default String toPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
