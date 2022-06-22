package nl.hybrit.lightsaber.shop.mapper;

import nl.hybrit.lightsaber.shop.controller.model.PadawanUserModel;
import nl.hybrit.lightsaber.shop.repository.RoleRepository;
import nl.hybrit.lightsaber.shop.repository.enums.RoleType;
import nl.hybrit.lightsaber.shop.repository.model.Role;
import nl.hybrit.lightsaber.shop.repository.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring", imports = {BCryptPasswordEncoder.class})
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", expression = "java(new BCryptPasswordEncoder().encode(model.getPassword()))")
    UserEntity map(PadawanUserModel model);

}
