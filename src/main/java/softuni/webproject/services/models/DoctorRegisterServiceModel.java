package softuni.webproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorRegisterServiceModel extends BaseServiceModel {
    private String name;
    private String specialization;
    private String description;
    private String logInKey;
}
