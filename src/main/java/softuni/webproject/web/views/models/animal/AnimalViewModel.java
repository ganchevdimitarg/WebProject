package softuni.webproject.web.views.models.animal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.webproject.data.models.Doctor;

@Getter
@Setter
@NoArgsConstructor
public class AnimalViewModel {
    private String breed;
    private String name;
    private double age;
    private Doctor doctor;
    private String disease;
}
