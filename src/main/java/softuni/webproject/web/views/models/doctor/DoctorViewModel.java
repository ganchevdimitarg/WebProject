package softuni.webproject.web.views.models.doctor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorViewModel {
    private String name;
    private String specialization;
    private String description;
}
