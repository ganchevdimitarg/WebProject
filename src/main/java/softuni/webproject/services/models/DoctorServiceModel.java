package softuni.webproject.services.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorServiceModel extends BaseServiceModel {
    private String name;
    private String specialization;
    private String description;
    private String logInKey;
    private String imageUrl;
}
