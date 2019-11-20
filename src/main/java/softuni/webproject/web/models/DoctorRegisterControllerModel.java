package softuni.webproject.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorRegisterControllerModel extends BaseControllerModel {
    private String name;
    private String specialization;
    private String description;
}
