package softuni.webproject.services.services.user;

import softuni.webproject.services.models.CurrentUser;
import softuni.webproject.services.models.UserServiceModel;

import java.util.List;

public interface UserService {
    void save(UserServiceModel model);

    void deleteUser(String username);

    void update(UserServiceModel model);

    void deletePet(String name, CurrentUser currentUser);

    boolean existsUserByUsername(String username);

   UserServiceModel findByUsernameAndPassword(String username, String password);

   UserServiceModel findByUsername(String username);

    List<UserServiceModel> getAll();
}
