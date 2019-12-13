package softuni.webproject.services.services.auth.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import softuni.webproject.services.models.BaseServiceModel;
import softuni.webproject.services.services.auth.AuthValidationService;
import softuni.webproject.services.services.doctor.DoctorService;
import softuni.webproject.services.services.user.UserService;

@Service
@AllArgsConstructor
public class AuthValidationServiceImpl implements AuthValidationService {
    private final static String NOT_AVAILABLE_USERNAME = "Username is not available";
    private final static String PASSWORD_NOT_MATCH = "Password and confirmation password not match";

    private final DoctorService doctorService;
    private final UserService userService;

    @Override
    public boolean isValid(BaseServiceModel model) throws IllegalArgumentException {
        return isPasswordValid(model.getPassword(), model.getConfirmPassword()) &&
                isUsernameFree(model.getUsername());
    }

    private boolean isUsernameFree(String username) throws IllegalArgumentException {
        if (doctorService.existsDoctorByUsername(username) || userService.existsUserByUsername(username)){
            throw new IllegalArgumentException(NOT_AVAILABLE_USERNAME);
        }
        return true;
    }

    private boolean isPasswordValid(String password, String confirmPassword) throws IllegalArgumentException {
        if (!password.equals(confirmPassword)){
            throw new IllegalArgumentException(PASSWORD_NOT_MATCH);
        }
        return true;
    }


}
