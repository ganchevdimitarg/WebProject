package softuni.webproject.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserViewModel {
    private String id;
    private String username;
    private String email;
    private String address;
    private String phoneNumber;
}
