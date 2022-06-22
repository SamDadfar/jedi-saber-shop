package nl.hybrit.lightsaber.shop.controller.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * OrderResponse payload class
 */
@Getter
@Setter
public class AddedOrderResponseModel {
    private String message;
    private List<String> lightSaberNames;
}
