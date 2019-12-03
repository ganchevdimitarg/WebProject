package softuni.webproject.services.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.data.models.IdentificationKey;
import softuni.webproject.data.models.User;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.data.repositories.IdentificationKeyRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.services.models.BaseServiceModel;
import softuni.webproject.services.services.auth.AuthValidationService;
import softuni.webproject.services.services.auth.HashingService;
import softuni.webproject.services.services.auth.impl.AuthServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthServiceImplTest {
    AuthValidationService validation;
    DoctorRepository doctorRepository;
    UserRepository userRepository;
    IdentificationKeyRepository keyRepository;
    HashingService hashingService;

    AuthServiceImpl service;

    @BeforeEach
    void setup(){
        validation = Mockito.mock(AuthValidationService.class);
        doctorRepository = Mockito.mock(DoctorRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        keyRepository = Mockito.mock(IdentificationKeyRepository.class);
        hashingService = Mockito.mock(HashingService.class);
        ModelMapper modelMapper = new ModelMapper();

        service = new AuthServiceImpl(validation, doctorRepository, userRepository, keyRepository, modelMapper,
                hashingService);
    }

    @Test
    void registerDoctor() {

    }

    //TODO
    @Test
    void registerUser() throws IllegalAccessException {
        BaseServiceModel model = new BaseServiceModel();
        model.setUsername("ivan");
        model.setPassword("123456q");

        Mockito.when(validation.isValid(model)).thenReturn(true);

        User user = new User();
        user.setUsername("ivan");
        user.setPassword("123456q");

        Mockito.when(userRepository.saveAndFlush(user)).thenReturn(user);

        assertEquals(model.getUsername(), user.getUsername());
    }

    @Test
    void logIn_whenUserTriesToLogIn_shouldCan() {
        String password = "123456q";
        String name = "ivan";
        User user = new User();
        user.setUsername("ivan");
        user.setPassword("123456q");

        Mockito.when(userRepository.findByUsernameAndPassword(user.getName(), password)).thenReturn(user);

        assertEquals(name, user.getUsername());
    }

    @Test
    void logIn_whenDoctorTriesToLogIn_shouldCan() {
        String password = "123456q";
        String name = "ivan";

        Doctor doctor = new Doctor();
        doctor.setUsername("ivan");
        doctor.setPassword("123456q");

        IdentificationKey key = new IdentificationKey();
        key.setLogKey("126qqq");
        Mockito.when(keyRepository.findByLogKey("126qqq")).thenReturn(key);

        if (keyRepository.findByLogKey("123456q") != null) {
            Mockito.when(doctorRepository.findByUsernameAndPassword(doctor.getName(), password)).thenReturn(doctor);
        }

        assertEquals(name, doctor.getUsername());
    }
}