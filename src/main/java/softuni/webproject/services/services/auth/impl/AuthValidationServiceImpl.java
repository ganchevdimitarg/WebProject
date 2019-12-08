package softuni.webproject.services.services.auth.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.services.models.BaseServiceModel;
import softuni.webproject.services.services.auth.AuthValidationService;
import softuni.webproject.services.services.doctor.DoctorService;

@Service
@AllArgsConstructor
public class AuthValidationServiceImpl implements AuthValidationService {
    private final static String NOT_AVAILABLE = "Username is not available";
    private final static String PASSWORD_NOT_MATCH = "Password and confirmation password not match";

    private final DoctorService doctorService;
    private final UserRepository userRepository;

    @Override
    public boolean isValid(BaseServiceModel model) throws IllegalAccessException {
        return this.isPasswordValid(model.getPassword(), model.getConfirmPassword()) &&
                this.isUsernameFree(model.getUsername());
    }

    private boolean isUsernameFree(String username) throws IllegalAccessException {
        if (doctorService.existsDoctorByUsername(username) || userRepository.existsUserByUsername(username)){
            throw new IllegalAccessException(NOT_AVAILABLE);
        }
        return true;
    }

    private boolean isPasswordValid(String password, String confirmPassword) throws IllegalAccessException {
        if (!password.equals(confirmPassword)){
            throw new IllegalAccessException(PASSWORD_NOT_MATCH);
        }
        return true;
    }


}
