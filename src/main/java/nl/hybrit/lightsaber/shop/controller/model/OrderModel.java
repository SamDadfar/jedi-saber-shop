package nl.hybrit.lightsaber.shop.controller.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.hybrit.lightsabershop.model.Sabers;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Order Model
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderModel {

    @NotNull
    private Sabers sabers;
    @NotNull
    private long padawanId;

}
