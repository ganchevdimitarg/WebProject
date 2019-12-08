package softuni.webproject.services.services.auth;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.services.models.BaseServiceModel;
import softuni.webproject.services.services.auth.impl.AuthValidationServiceImpl;
import softuni.webproject.services.base.TestBase;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthValidationServiceImplTest extends TestBase {

    @MockBean
    DoctorRepository doctorRepository;
    @MockBean
    UserRepository userRepository;

    @Autowired
    AuthValidationServiceImpl service;

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