package softuni.webproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OwnerServiceModel {
    private String username;
    private String password;
    private String email;
    private String name;
    private String address;
    private String phoneNumber;
}
