package softuni.webproject.services.services.auth;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.webproject.data.models.IdentificationKey;
import softuni.webproject.errors.LogInHandleException;
import softuni.webproject.services.base.TestBase;
import softuni.webproject.services.models.*;
import softuni.webproject.services.services.auth.impl.AuthValidationServiceImpl;
import softuni.webproject.services.services.doctor.DoctorService;
import softuni.webproject.services.services.doctor.IdentificationKeyService;
import softuni.webproject.services.services.user.UserService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceImplTest extends TestBase {
    @MockBean
    AuthValidationServiceImpl validation;
    @MockBean
    DoctorService doctorService;
    @MockBean
    UserService userService;
    @MockBean
    IdentificationKeyService keyService;

    @Autowired
    AuthService service;

    @Test
    void registerDoctor_whenDoctorIsValid_shouldRegister() throws IllegalAccessException {
        String keyName = "aAa2aF";
        IdentificationKey key = new IdentificationKey();
        key.setLogKey(keyName);
        DoctorServiceModel doctorServiceModel = new DoctorServiceModel("Dimitar", "vet",
                "The best", key.getLogKey(), "");
        doctorServiceModel.setPassword("11111q");
        Mockito.when(validation.isValid(doctorServiceModel)).thenReturn(true);

        service.registerDoctor(doctorServiceModel);

        ArgumentCaptor<DoctorServiceModel> argument = ArgumentCaptor.forClass(DoctorServiceModel.class);
        Mockito.verify(doctorService).save(argument.capture());

        DoctorServiceModel doctor = argument.getValue();
        assertNotNull(doctor);
    }

    @Test
    void registerDoctor_whenDoctorIsNotValid_shouldThrowException() {
        String keyName = "aAa2aF";
        IdentificationKey key = new IdentificationKey();
        key.setLogKey(keyName);
        DoctorServiceModel doctorServiceModel = new DoctorServiceModel("Dimitar", "vet",
                "The best", key.getLogKey(), "");
        doctorServiceModel.setPassword("11111q");
        Mockito.when(validation.isValid(doctorServiceModel)).thenReturn(false);

        assertThrows(IllegalArgumentException.class ,() -> service.registerDoctor(doctorServiceModel));
    }

    @Test
    void registerUser_whenUserIsValid_shouldRegister() throws IllegalArgumentException {
        UserServiceModel userServiceModel = new UserServiceModel( "Dimitar", "dimitar@gmail.com",
                "Vladislav Varnenchik", "0888888888", new ArrayList<>(),"");
        userServiceModel.setPassword("11111q");
        Mockito.when(validation.isValid(userServiceModel)).thenReturn(true);

        service.registerUser(userServiceModel);

        ArgumentCaptor<UserServiceModel> argument = ArgumentCaptor.forClass(UserServiceModel.class);
        Mockito.verify(userService).save(argument.capture());

        UserServiceModel user = argument.getValue();
        assertNotNull(user);
    }

    @Test
    void registerUser_whenUserIsNotValid_shouldThrowException() {
        UserServiceModel userServiceModel = new UserServiceModel( "Dimitar", "dimitar@gmail.com",
                "Vladislav Varnenchik", "0888888888", new ArrayList<>(),"");
        userServiceModel.setPassword("11111q");
        Mockito.when(validation.isValid(userServiceModel)).thenReturn(false);

        assertThrows(IllegalArgumentException.class ,() -> service.registerUser(userServiceModel));
    }

    @Test
    void logIn_whenUserLogIn_shouldLogin() throws LogInHandleException {
        String password = "123456q";
        String name = "ivan";
        UserServiceModel user = new UserServiceModel();
        user.setUsername(name);
        user.setPassword(password);
        user.setUsername(name);

        Mockito.when(userService.findByUsernameAndPassword(name, password)).thenReturn(user);

        CurrentUser currentUser = service.logIn(user);

        assertEquals(name, currentUser.getUsername());
    }

    @Test
    void logIn_whenDoctorLogIn_shouldLogin() throws LogInHandleException {
        String password = "123456q";
        String name = "ivan";
        String key = "ivAn1A";
        IdentificationKeyServiceModel identificationKey = new IdentificationKeyServiceModel();
        identificationKey.setLogKey(key);
        DoctorServiceModel doctor = new DoctorServiceModel(name,"", "", key, "");
        doctor.setUsername(name);
        doctor.setPassword(password);
        Mockito.when(keyService.findByKeyName(key)).thenReturn(identificationKey);
        Mockito.when(doctorService.findByUsernameAndPassword(name, password)).thenReturn(doctor);

        CurrentUser currentUser = service.logIn(doctor);

        assertEquals(name, currentUser.getUsername());
    }


}