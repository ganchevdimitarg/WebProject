package softuni.webproject.services.services.auth.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import softuni.webproject.data.repositories.IdentificationKeyRepository;
import softuni.webproject.errors.LogInHandleException;
import softuni.webproject.services.models.BaseServiceModel;
import softuni.webproject.services.models.CurrentUser;
import softuni.webproject.services.models.DoctorServiceModel;
import softuni.webproject.services.models.UserServiceModel;
import softuni.webproject.services.services.auth.AuthService;
import softuni.webproject.services.services.auth.AuthValidationService;
import softuni.webproject.services.services.auth.HashingService;
import softuni.webproject.services.services.doctor.DoctorService;
import softuni.webproject.services.services.doctor.IdentificationKeyService;
import softuni.webproject.services.services.user.UserService;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthValidationService validation;
    private final DoctorService doctorService;
    private final UserService userService;
    private final IdentificationKeyService keyService;
    private final IdentificationKeyRepository keyRepository;
    private final HashingService hashingService;

    @Override
    public void registerDoctor(DoctorServiceModel model) throws IllegalAccessException {
        if (!validation.isValid(model)) {
            return;
        }
        doctorService.createDoctor(model);
    }

    @Override
    public void registerUser(UserServiceModel model) throws IllegalAccessException {
        if (!validation.isValid(model)) {
            return;
        }
        model.setPassword(hashingService.hash(model.getPassword()));
        userService.save(model);
    }

    @Override
    public CurrentUser logIn(BaseServiceModel model) throws LogInHandleException {
        String pass = hashingService.hash(model.getPassword());
        try {
            if (!model.getLogInKey().isEmpty()) {
                DoctorServiceModel doctor = doctorService.findByUsernameAndPassword(model.getUsername(), pass);
                return new CurrentUser(doctor.getUsername());
            }

            UserServiceModel user = userService.findByUsernameAndPassword(model.getUsername(), pass);
            return new CurrentUser(user.getUsername());
        } catch (Exception e) {
            throw new LogInHandleException("Not found user! Try again");
        }
    }

}
