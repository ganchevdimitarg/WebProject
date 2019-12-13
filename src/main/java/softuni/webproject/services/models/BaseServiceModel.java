package softuni.webproject.services.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseServiceModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String logInKey;
}
