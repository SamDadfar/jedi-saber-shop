package nl.hybrit.lightsaber.shop.services;

import nl.hybrit.lightsaber.shop.repository.enums.CrystalEnum;
import nl.hybrit.lightsaber.shop.repository.model.PadawanEntity;
import nl.hybrit.lightsaber.shop.repository.model.SaberEntity;
import nl.hybrit.lightsabershop.model.Sabers;

import java.util.List;

public interface SaberService {
    final static int ADULT_FORCE = 180;

    List<SaberEntity> save(Sabers saberModelList);

    Sabers findAll();

    Sabers.Saber findById(long id);

    Sabers.Saber findBySaberId(int saberId);

    /**
     * Calculate price of ordered crystal based on given padawan and crystal type
     *
     * @param padawan     given padawan
     * @param crystalType given crystal type
     * @return calculated price
     */
    default double getSaberPrice(PadawanEntity padawan, CrystalEnum crystalType) {
        if (padawan.isAdult()) {
            return CrystalEnum.getPrice(ADULT_FORCE, crystalType.getValue());
        } else {
            return CrystalEnum.getPrice(padawan.getPower(), crystalType.getValue());
        }
    }
}
