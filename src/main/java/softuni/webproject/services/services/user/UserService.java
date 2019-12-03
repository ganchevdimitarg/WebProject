package softuni.webproject.services.services.user;

import softuni.webproject.services.models.UserServiceModel;

public interface UserService {
    UserServiceModel findByUsername(String name);

    void deleteUser(String username);

    void update(UserServiceModel model);
//    TODO
//    void deletePet(String username, String animal);
}
