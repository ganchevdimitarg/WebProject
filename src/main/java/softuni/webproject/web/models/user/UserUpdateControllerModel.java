package softuni.webproject.web.models.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import static softuni.webproject.config.Constant.EMAIL_VALIDATE;
import static softuni.webproject.config.Constant.PHONE_NUMBER_VALIDATE;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateControllerModel {
    @Pattern(regexp = EMAIL_VALIDATE)
    @NotEmpty
    private String email;
    @NotEmpty
    private String address;
    @Pattern(regexp = PHONE_NUMBER_VALIDATE)
    @NotEmpty
    private String phoneNumber;
}