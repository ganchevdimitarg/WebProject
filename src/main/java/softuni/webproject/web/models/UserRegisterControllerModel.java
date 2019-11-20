package softuni.webproject.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterControllerModel extends BaseControllerModel {
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
}
