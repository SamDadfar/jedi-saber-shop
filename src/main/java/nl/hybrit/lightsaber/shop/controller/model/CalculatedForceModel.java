package nl.hybrit.lightsaber.shop.controller.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CalculatedForceModel {

    private int force;
    private float neededForce;
    private String crystalType;
    private String crystalPrice;

}
