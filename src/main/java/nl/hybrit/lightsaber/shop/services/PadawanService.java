package nl.hybrit.lightsaber.shop.services;

import nl.hybrit.lightsaber.shop.controller.model.PadawanUserModel;
import nl.hybrit.lightsaber.shop.repository.enums.CrystalEnum;
import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;

public interface PadawanService {


    /**
     * Find Padawan by id
     *
     * @param id given id
     * @return found Padawan
     */
    PadawanEntity findById(long id);

    /**
     * saves or update padawan
     * @param model given padawan
     * @return saved or updated padawan
     */
    PadawanEntity save(PadawanUserModel model);
    /**
     * Calculate needed force bases on crystal type
     * @param padawan given padawan
     * @param crystalType given crystal type
     * @return calculated force
     */
    default float getNeededForce(PadawanEntity padawan, CrystalEnum crystalType) {
        return CrystalEnum.getForceNeeded(padawan.getForce(), crystalType.getValue());
    }

}
