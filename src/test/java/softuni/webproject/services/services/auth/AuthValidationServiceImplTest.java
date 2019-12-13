package softuni.webproject.services.services.auth;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.services.base.TestBase;
import softuni.webproject.services.models.BaseServiceModel;
import softuni.webproject.services.services.auth.impl.AuthValidationServiceImpl;
import softuni.webproject.services.services.doctor.DoctorService;
import softuni.webproject.services.services.user.UserService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthValidationServiceImplTest extends TestBase {

    @MockBean
    DoctorService doctorService;
    @MockBean
    UserService userService;

    @Autowired
    AuthValidationServiceImpl service;

    @Test
    void isValid_whenPasswordsIsNOTSame_shouldThrowException() {
        BaseServiceModel baseServiceModel = new BaseServiceModel();
        baseServiceModel.setPassword("1");
        baseServiceModel.setConfirmPassword("2");
        baseServiceModel.setUsername("Iva");

        assertThrows(IllegalArgumentException.class, () -> service.isValid(baseServiceModel));
    }

    @Test
    void isValid_whenPasswordsIsSameAndUsernameNOTFree_shouldThrowException() {
        BaseServiceModel baseServiceModel = new BaseServiceModel();
        baseServiceModel.setPassword("1");
        baseServiceModel.setConfirmPassword("1");
        baseServiceModel.setUsername("Ivan");

        Doctor doctor = new Doctor();
        doctor.setName("Ivan");
        Mockito.when(doctorService.existsDoctorByUsername("Ivan")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.isValid(baseServiceModel));
    }

    @Test
    void isValid_whenPasswordsIsSameAndUsernameIsFree_shouldBeTrue() {
        BaseServiceModel baseServiceModel = new BaseServiceModel();
        baseServiceModel.setPassword("1");
        baseServiceModel.setConfirmPassword("1");
        baseServiceModel.setUsername("Ivan");

        Doctor doctor = new Doctor();
        doctor.setName("Ivan");
        Mockito.when(doctorService.existsDoctorByUsername("Ivan")).thenReturn(false);
        Mockito.when(userService.existsUserByUsername("Ivan")).thenReturn(false);
        assertTrue(service.isValid(baseServiceModel));
    }
}