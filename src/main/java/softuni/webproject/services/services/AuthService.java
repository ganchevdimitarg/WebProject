package softuni.webproject.services.services;

import softuni.webproject.services.models.BaseServiceModel;
import softuni.webproject.services.models.DoctorRegisterServiceModel;
import softuni.webproject.services.models.LogInServiceModel;
import softuni.webproject.services.models.UserRegisterServiceModel;

public interface AuthService {
    void registerDoctor(DoctorRegisterServiceModel model) throws IllegalAccessException;

    void registerUser(UserRegisterServiceModel model) throws IllegalAccessException;

    LogInServiceModel logIn(BaseServiceModel model);


}
