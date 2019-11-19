package softuni.webproject.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpDoctorModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String name;
    private String specialization;
    private String description;
}
