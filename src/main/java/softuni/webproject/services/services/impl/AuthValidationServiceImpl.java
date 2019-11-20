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
    public boolean isValid(BaseServiceModel model) {
        return this.isPasswordValid(model.getPassword(), model.getConfirmPassword()) &&
                this.isUsernameFree(model.getUsername());
    }

    //TODO
    private boolean isUsernameFree(String username) {
        return !doctorRepository.existsDoctorByUsername(username) || !userRepository.existsUserByUsername(username);
    }

    private boolean isPasswordValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
