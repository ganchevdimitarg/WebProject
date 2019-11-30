package softuni.webproject.services.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.data.models.LogInIdentificationKey;
import softuni.webproject.data.models.User;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.data.repositories.LogInIdentificationKeyRepository;
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
    private final LogInIdentificationKeyRepository keyRepository;
    private final ModelMapper modelMapper;
    private final HashingService hashingService;

    @Autowired
    public AuthServiceImpl(AuthValidationService validation,
                           DoctorRepository doctorRepository,
                           UserRepository userRepository,
                           LogInIdentificationKeyRepository keyRepository, ModelMapper modelMapper,
                           HashingService hashingService) {
        this.validation = validation;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.keyRepository = keyRepository;
        this.modelMapper = modelMapper;
        this.hashingService = hashingService;
    }

    @Override
    public void registerDoctor(DoctorRegisterServiceModel model) throws IllegalAccessException {
        if (!validation.isValid(model)) {
            return;
        }
        LogInIdentificationKey key = keyRepository.findByLogKey(model.getLogInKey());
        if (key != null) {
            Doctor doctor = modelMapper.map(model, Doctor.class);
            doctor.setPassword(hashingService.hash(doctor.getPassword()));
            doctor.setLogInKey(key);
            doctorRepository.save(doctor);
            key.setFree(false);
            keyRepository.saveAndFlush(key);
        }
    }

    @Override
    public void registerUser(UserRegisterServiceModel model) throws IllegalAccessException {
        if (!validation.isValid(model)) {
            return;
        }
        User user = modelMapper.map(model, User.class);
        user.setPassword(hashingService.hash(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    @Override
    public LogInServiceModel logIn(BaseServiceModel model) {
        String pass = hashingService.hash(model.getPassword());
        User user = userRepository.findByUsernameAndPassword(model.getUsername(), pass);
        if (keyRepository.findByLogKey(model.getLogInKey()) != null) {
            Doctor doctor = doctorRepository.findByUsernameAndPassword(model.getUsername(), pass);
            return new LogInServiceModel(doctor.getName());
        }
        return new LogInServiceModel(user.getName());
    }


}
