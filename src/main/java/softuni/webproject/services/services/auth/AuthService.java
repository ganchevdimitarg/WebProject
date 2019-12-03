package softuni.webproject.services.services.auth;

import softuni.webproject.services.models.BaseServiceModel;
import softuni.webproject.services.models.DoctorServiceModel;
import softuni.webproject.services.models.CurrentUser;
import softuni.webproject.services.models.UserServiceModel;

public interface AuthService {
    void registerDoctor(DoctorServiceModel model) throws IllegalAccessException;

    void registerUser(UserServiceModel model) throws IllegalAccessException;

    CurrentUser logIn(BaseServiceModel model);


}
