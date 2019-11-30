package softuni.webproject.services.services.impl;

import org.springframework.stereotype.Service;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.services.models.BaseServiceModel;
import softuni.webproject.services.services.AuthValidationService;

@Service
public class AuthValidationServiceImpl implements AuthValidationService {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public AuthValidationServiceImpl(DoctorRepository doctorRepository, UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(BaseServiceModel model) throws IllegalAccessException {
        return this.isPasswordValid(model.getPassword(), model.getConfirmPassword()) &&
                this.isUsernameFree(model.getUsername());
    }

    private boolean isUsernameFree(String username) throws IllegalAccessException {
        if (doctorRepository.existsDoctorByUsername(username) || userRepository.existsUserByUsername(username)){
            throw new IllegalAccessException("Username is not available");
        }
        return true;
    }

    private boolean isPasswordValid(String password, String confirmPassword) throws IllegalAccessException {
        if (!password.equals(confirmPassword)){
            throw new IllegalAccessException("Password and confirmation password not match");
        }
        return true;
    }


}
