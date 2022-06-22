package nl.hybrit.lightsaber.shop.mapper;

import nl.hybrit.lightsaber.shop.repository.enums.CrystalEnum;
import nl.hybrit.lightsaber.shop.repository.model.SaberEntity;
import nl.hybrit.lightsabershop.model.CrystalType;
import nl.hybrit.lightsabershop.model.Sabers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SaberMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "saberId", source = "id")
    @Mapping(target = "crystalType", source = "crystal.color")
    @Mapping(target = "crystalName", source = "crystal.name")
    SaberEntity map(Sabers.Saber saber);

    @Mapping(target = "id", source = "saberId")
    @Mapping(target = "crystal.color", source = "crystalType")
    @Mapping(target = "crystal.name", source = "crystalName")
    Sabers.Saber map(SaberEntity saberEntity);

    default CrystalEnum toCrystalType(CrystalType crystalType) {
        return CrystalEnum.fromValue(crystalType.value());
    }

    default CrystalType toColor(CrystalEnum crystalEnum) {
        return CrystalType.fromValue(crystalEnum.getValue());
    }

    default List<SaberEntity> toSaberList(Sabers sabers) {
        return sabers.getSaber().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    default Sabers toSaberList(List<SaberEntity> saberEntities) {
        List<Sabers.Saber> saberList = saberEntities.stream()
                .map(this::map)
                .collect(Collectors.toList());
        Sabers sabers = new Sabers();
        List<Sabers.Saber> list =sabers.getSaber();
        list.addAll(saberList);
        return sabers;
    }
}
