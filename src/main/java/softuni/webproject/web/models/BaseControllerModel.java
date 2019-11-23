package softuni.webproject.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static softuni.webproject.config.Constant.*;

@Getter
@Setter
@NoArgsConstructor
public class BaseControllerModel {
    @Size(min = 3, max = 20, message = INVALID_USERNAME_OR_PASSWORD_MASSAGE )
    private String username;
    @Pattern(regexp = PASSWORD_VALIDATE, message = INVALID_USERNAME_OR_PASSWORD_MASSAGE)
    private String password;
    private String confirmPassword;
    private String logInKey;
}
