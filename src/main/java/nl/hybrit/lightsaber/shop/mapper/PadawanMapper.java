package nl.hybrit.lightsaber.shop.mapper;

import nl.hybrit.lightsaber.shop.controller.model.PadawanUserModel;
import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.Period;

@Mapper(componentModel = "spring")
public interface PadawanMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "age", source = "model.dateOfBirth")
    PadawanEntity map(PadawanUserModel model);

    default Integer toAge(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirth, currentDate).getYears();
    }
}
