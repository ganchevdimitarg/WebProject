package softuni.webproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorServiceModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String name;
    private String specialization;
    private String description;
}
