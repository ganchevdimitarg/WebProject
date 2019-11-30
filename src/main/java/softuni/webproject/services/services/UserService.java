package softuni.webproject.services.services;

import softuni.webproject.services.models.UserRegisterServiceModel;

public interface UserService {
    UserRegisterServiceModel findByName(String name);

    void deleteUser(String id);
}
