package softuni.webproject.web.views.models.animal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnimalViewModel {
    private String breed;
    private String name;
    private Integer age;
    private String doctor;
    private String disease;
    private String medicine;
}
