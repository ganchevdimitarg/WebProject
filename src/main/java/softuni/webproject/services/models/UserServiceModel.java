package softuni.webproject.services.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceModel extends BaseServiceModel {
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private List<AnimalServiceModel> animals;
    private String imageUrl;
}
