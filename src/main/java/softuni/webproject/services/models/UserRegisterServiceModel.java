package softuni.webproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterServiceModel extends BaseServiceModel {
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
}
