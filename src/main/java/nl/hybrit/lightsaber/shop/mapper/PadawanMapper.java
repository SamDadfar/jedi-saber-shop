package nl.hybrit.lightsaber.shop.mapper;

import nl.hybrit.lightsaber.shop.controller.model.PadawanUserModel;
import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;
import nl.hybrit.lightsaber.shop.repository.model.UserEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.Period;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PadawanMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "model")
    PadawanEntity map(PadawanUserModel model);

}
