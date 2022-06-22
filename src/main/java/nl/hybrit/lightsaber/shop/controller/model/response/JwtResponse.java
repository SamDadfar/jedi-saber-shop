package nl.hybrit.lightsaber.shop.controller.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private UserPojo user;

    public JwtResponse(String accessToken, UserPojo user) {
        this.token = accessToken;
        this.user = user;
    }
}
