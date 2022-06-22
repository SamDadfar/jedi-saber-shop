package nl.hybrit.lightsaber.shop.mapper;


import org.mapstruct.Mapper;

/**
 * Order mapper
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {

//
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "padawan", source = "padawanId")
//    OrderEntity map(Sabers sabers, long padawanId);
//
//    default Padawan toPawadan(long id) throws NotFoundException {
//        return padawanRepository.findById(id).orElseThrow(() -> new NotFoundException("Padawan is not exist."));
//    }
}
