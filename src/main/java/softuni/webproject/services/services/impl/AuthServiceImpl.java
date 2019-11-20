package softuni.webproject.services.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.data.models.User;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.services.models.BaseServiceModel;
import softuni.webproject.services.models.DoctorRegisterServiceModel;
import softuni.webproject.services.models.LogInServiceModel;
import softuni.webproject.services.models.UserRegisterServiceModel;
import softuni.webproject.services.services.AuthService;
import softuni.webproject.services.services.AuthValidationService;
import softuni.webproject.services.services.HashingService;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthValidationService validation;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final HashingService hashingService;


    public AuthServiceImpl(AuthValidationService validation,
                           DoctorRepository doctorRepository,
                           UserRepository userRepository,
                           ModelMapper modelMapper,
                           HashingService hashingService) {
        this.validation = validation;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.hashingService = hashingService;
    }

    @Override
    public void registerDoctor(DoctorRegisterServiceModel model) {
        if (!validation.isValid(model)) {
            return;
        }
        Doctor doctor = this.modelMapper.map(model, Doctor.class);
        doctor.setPassword(hashingService.hash(doctor.getPassword()));
        doctorRepository.save(doctor);
    }

    @Override
    public void registerUser(UserRegisterServiceModel model) {
        if (!validation.isValid(model)) {
            return;
        }

        User user = this.modelMapper.map(model, User.class);
        user.setPassword(hashingService.hash(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public LogInServiceModel logIn(BaseServiceModel model) {
        String pass = hashingService.hash(model.getPassword());
        Doctor doctor = doctorRepository.findByUsernameAndPassword(model.getUsername(), pass);
        if (doctor == null) {
            User user = userRepository.findByUsernameAndPassword(model.getUsername(), pass);
            return new LogInServiceModel(user.getName());
        }
        return new LogInServiceModel(doctor.getName());
    }



}
