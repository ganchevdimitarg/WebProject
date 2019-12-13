package softuni.webproject.services.services.auth;

import softuni.webproject.errors.LogInHandleException;
import softuni.webproject.services.models.BaseServiceModel;
import softuni.webproject.services.models.DoctorServiceModel;
import softuni.webproject.services.models.CurrentUser;
import softuni.webproject.services.models.UserServiceModel;

public interface AuthService {
    void registerDoctor(DoctorServiceModel model) throws IllegalArgumentException;

    void registerUser(UserServiceModel model) throws IllegalArgumentException;

    CurrentUser logIn(BaseServiceModel model) throws LogInHandleException;


}
