package nl.hybrit.lightsaber.shop.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Padawan model class uses as payload
 */
@Getter
@Setter
public class PadawanUserModel {

    @NotNull
    private String username;
    private String password;
    @Email
    private String email;
    private String
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

}
