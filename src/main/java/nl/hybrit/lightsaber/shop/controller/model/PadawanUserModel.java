package nl.hybrit.lightsaber.shop.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

/**
 * Padawan model class uses as payload
 */
@Getter
@Setter
public class PadawanUserModel {

    @NotNull
    private String username;
    @NotNull
    private String password;
    @Email
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private Set<String> roles;
}
