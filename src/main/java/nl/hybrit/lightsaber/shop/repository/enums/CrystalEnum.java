package nl.hybrit.lightsaber.shop.repository.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CrystalEnum {
    RED("red", "Ilum Crystal", 20, 101),
    BLUE("blue", "Dantooine Crystal", 19, 10),
    GREEN("green", "Dagobah Crystal", 22, 37);

    private final String value;
    private final String type;
    private final int powerPercentage;
    private final int powerOfCrystal;

    public static CrystalEnum fromValue(String v) {
        for (CrystalEnum c : CrystalEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }


    /**
     * Get price which determines based on given Padawan's force and desired crystal
     *
     * @param force given padawan's force
     * @param color given color of crystal
     * @return price
     */
    public static double getPrice(int force, String color) {
        CrystalEnum crystalEnum = fromValue(color);
        return crystalEnum.getPowerOfCrystal() * getForceNeeded(force, color);
    }

    public static float getForceNeeded(int force, String color) {
        CrystalEnum crystalEnum = fromValue(color);
        return (float) ((crystalEnum.getPowerPercentage() * force) / 100);
    }
}
