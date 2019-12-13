package softuni.webproject.services.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.webproject.data.models.Doctor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalServiceModel {
    private String breed;
    private String name;
    private double age;
    private Doctor doctor;
    private String disease;
    private String user;
    private List<MedicineServiceModel> medicines;
}
