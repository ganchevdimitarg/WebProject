package softuni.webproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.webproject.data.models.Doctor;

@Getter
@Setter
@NoArgsConstructor
public class AnimalServiceModel {
    private String breed;
    private String name;
    private double age;
    private Doctor doctor;
    private String disease;
    private String medicine;
    private String user;
}
