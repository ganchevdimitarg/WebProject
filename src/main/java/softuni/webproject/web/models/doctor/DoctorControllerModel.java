package softuni.webproject.web.models.doctor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.webproject.web.models.BaseControllerModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static softuni.webproject.config.Constant.INVALID_TEXT_LENGTH_MASSAGE;

@Getter
@Setter
@NoArgsConstructor
public class DoctorControllerModel extends BaseControllerModel {
    @NotEmpty
    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    private String name;
    @NotEmpty
    @Size(min = 3, max = 6, message = INVALID_TEXT_LENGTH_MASSAGE)
    private String specialization;
    @NotEmpty
    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    private String description;
}
