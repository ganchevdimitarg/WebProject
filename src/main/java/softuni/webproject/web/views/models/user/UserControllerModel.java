package softuni.webproject.web.views.models.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import softuni.webproject.web.views.models.BaseControllerModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static softuni.webproject.config.Constant.*;

@Getter
@Setter
@NoArgsConstructor
public class UserControllerModel extends BaseControllerModel {
    @NotEmpty
    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    private String name;

    @Pattern(regexp = EMAIL_VALIDATE)
    @NotEmpty
    private String email;

    @NotEmpty
    private String address;

    @Pattern(regexp = PHONE_NUMBER_VALIDATE)
    @NotEmpty
    private String phoneNumber;

    private MultipartFile image;
}
