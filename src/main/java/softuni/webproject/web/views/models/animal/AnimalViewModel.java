package softuni.webproject.web.views.models.animal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.services.models.MedicineServiceModel;
import softuni.webproject.web.views.models.medicine.MedicineViewModel;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AnimalViewModel {
    private String breed;
    private String name;
    private double age;
    private Doctor doctor;
    private String disease;
    private List<MedicineViewModel> medicines;
}
