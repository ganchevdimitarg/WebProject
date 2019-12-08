package softuni.webproject.services.services.auth;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.data.models.IdentificationKey;
import softuni.webproject.data.models.User;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.data.repositories.IdentificationKeyRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.services.base.TestBase;
import softuni.webproject.services.models.DoctorServiceModel;
import softuni.webproject.services.models.UserServiceModel;
import softuni.webproject.services.services.auth.impl.AuthValidationServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceImplTest extends TestBase {
    @MockBean
    AuthValidationServiceImpl validation;
    @MockBean
    DoctorRepository doctorRepository;
    @MockBean
    UserRepository userRepository;
    @MockBean
    IdentificationKeyRepository keyRepository;

    @Autowired
    AuthService service;

    @Test
    void registerDoctor_whenDoctorIsValid_shouldThrow() throws IllegalAccessException {
        String keyName = "aAa2aF";
        IdentificationKey key = new IdentificationKey();
        key.setLogKey(keyName);
        DoctorServiceModel doctorServiceModel = new DoctorServiceModel("Dimitar", "vet",
                "The best", key.getLogKey());
        doctorServiceModel.setPassword("11111q");
        Mockito.when(keyRepository.findByLogKey(keyName)).thenReturn(key);
        Mockito.when(validation.isValid(doctorServiceModel)).thenReturn(true);

        service.registerDoctor(doctorServiceModel);

        ArgumentCaptor<Doctor> argument = ArgumentCaptor.forClass(Doctor.class);
        Mockito.verify(doctorRepository).save(argument.capture());

        Doctor doctor = argument.getValue();
        assertNotNull(doctor);
    }

    @Test
    void registerUser_whenUserIsValid_shouldPast() throws IllegalAccessException {
        UserServiceModel userServiceModel = new UserServiceModel("Dimitar", "dimitar@gmail.com",
                "Vladislav Varnenchik", "0888888888");

        Mockito.when(validation.isValid(userServiceModel)).thenReturn(true);

        assertThrows(RuntimeException.class, () -> service.registerUser(userServiceModel));
    }

    @Test
    void logIn_whenUserLogIn_shouldPast() {
        String password = "123456q";
        String name = "ivan";

        User user = new User();
        user.setUsername("ivan");
        user.setPassword("123456q");

        Mockito.when(userRepository.findByUsernameAndPassword(name, password)).thenReturn(user);

        assertEquals(name, user.getUsername());
    }

    @Test
    void logIn_whenDoctorLogIn_shouldPast() {
        String password = "123456q";
        String name = "ivan";

        Doctor doctor = new Doctor();
        doctor.setUsername(name);
        doctor.setPassword(password);

        Mockito.when(doctorRepository.findByUsernameAndPassword(name, password)).thenReturn(doctor);

        assertEquals(name, doctor.getUsername());
    }
}