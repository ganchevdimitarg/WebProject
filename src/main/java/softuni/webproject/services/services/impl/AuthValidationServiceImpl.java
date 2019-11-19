package softuni.webproject.services.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.services.models.DoctorServiceModel;
import softuni.webproject.services.services.AuthValidationService;

@Service
public class AuthValidationServiceImpl implements AuthValidationService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public AuthValidationServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    @Override
    public boolean isValid(DoctorServiceModel model) {
        return this.isPasswordValid(model.getPassword(), model.getConfirmPassword()) &&
                this.isUsernameFree(model.getUsername());
    }

    private boolean isUsernameFree(String username) {
        return doctorRepository.findByUsername(username);
    }

    private boolean isPasswordValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
