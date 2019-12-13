package softuni.webproject.web.views.models.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserViewModel {
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String imageUrl;
}
