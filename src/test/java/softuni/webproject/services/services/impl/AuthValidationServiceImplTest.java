package softuni.webproject.services.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.services.models.BaseServiceModel;
import softuni.webproject.services.services.auth.impl.AuthValidationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthValidationServiceImplTest {

    AuthValidationServiceImpl service;
    DoctorRepository doctorRepository;
    UserRepository userRepository;

    @BeforeEach
    void setup(){
        doctorRepository = Mockito.mock(DoctorRepository.class);
        userRepository = Mockito.mock(UserRepository.class);

        service = new AuthValidationServiceImpl(doctorRepository, userRepository);
    }

    @Test
    void isValid_whenPasswordsIsNOTSame_shouldThrowException() {
        BaseServiceModel baseServiceModel = new BaseServiceModel();
        baseServiceModel.setPassword("1");
        baseServiceModel.setConfirmPassword("2");
        baseServiceModel.setUsername("Iva");

        assertThrows(IllegalAccessException.class, () -> service.isValid(baseServiceModel));
    }

    @Test
    void isValid_whenPasswordsIsSameAndUsernameNOTFree_shouldThrowException() {
        BaseServiceModel baseServiceModel = new BaseServiceModel();
        baseServiceModel.setPassword("1");
        baseServiceModel.setConfirmPassword("1");
        baseServiceModel.setUsername("Ivan");

        Doctor doctor = new Doctor();
        doctor.setName("Ivan");
        Mockito.when(doctorRepository.existsDoctorByUsername("Ivan")).thenReturn(true);

        assertThrows(IllegalAccessException.class, () -> service.isValid(baseServiceModel));
    }
}