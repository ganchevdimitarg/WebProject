package softuni.webproject.services.services.auth.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.data.models.IdentificationKey;
import softuni.webproject.data.models.User;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.data.repositories.IdentificationKeyRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.services.models.BaseServiceModel;
import softuni.webproject.services.models.DoctorServiceModel;
import softuni.webproject.services.models.CurrentUser;
import softuni.webproject.services.models.UserServiceModel;
import softuni.webproject.services.services.auth.AuthService;
import softuni.webproject.services.services.auth.AuthValidationService;
import softuni.webproject.services.services.auth.HashingService;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthValidationService validation;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final IdentificationKeyRepository keyRepository;
    private final ModelMapper modelMapper;
    private final HashingService hashingService;

    @Autowired
    public AuthServiceImpl(AuthValidationService validation,
                           DoctorRepository doctorRepository,
                           UserRepository userRepository,
                           IdentificationKeyRepository keyRepository, ModelMapper modelMapper,
                           HashingService hashingService) {
        this.validation = validation;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.keyRepository = keyRepository;
        this.modelMapper = modelMapper;
        this.hashingService = hashingService;
    }

    @Override
    public void registerDoctor(DoctorServiceModel model) throws IllegalAccessException {
        if (!validation.isValid(model)) {
            return;
        }
        IdentificationKey key = keyRepository.findByLogKey(model.getLogInKey());
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
    public void registerUser(UserServiceModel model) throws IllegalAccessException {
        if (!validation.isValid(model)) {
            return;
        }
        User user = modelMapper.map(model, User.class);
        user.setPassword(hashingService.hash(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    @Override
    public CurrentUser logIn(BaseServiceModel model) {
        String pass = hashingService.hash(model.getPassword());
        User user = userRepository.findByUsernameAndPassword(model.getUsername(), pass);
        if (keyRepository.findByLogKey(model.getLogInKey()) != null) {
            Doctor doctor = doctorRepository.findByUsernameAndPassword(model.getUsername(), pass);
            return new CurrentUser(doctor.getUsername());
        }
        return new CurrentUser(user.getUsername());
    }


}
