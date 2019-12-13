package softuni.webproject.services.services.doctor;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.data.models.IdentificationKey;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.data.repositories.IdentificationKeyRepository;
import softuni.webproject.services.base.TestBase;
import softuni.webproject.services.models.DoctorServiceModel;
import softuni.webproject.services.services.auth.HashingService;

import static org.junit.jupiter.api.Assertions.*;

class DoctorServiceImplTest extends TestBase {
    @MockBean
    DoctorRepository doctorRepository;
    @MockBean
    IdentificationKeyRepository keyRepository;
    @MockBean
    HashingService hashingService;

    @Autowired
    DoctorService doctorService;

    @Test
    void save_whenDoctorHaveKey_shouldBeSave() {
        String keyName = "aAa2aF";
        IdentificationKey key = new IdentificationKey();
        key.setLogKey(keyName);
        DoctorServiceModel doctorServiceModel = new DoctorServiceModel("Dimitar", "vet",
                "The best", key.getLogKey(), "");
        doctorServiceModel.setPassword("11111q");
        Mockito.when(keyRepository.findByLogKey(keyName)).thenReturn(key);

        doctorService.save(doctorServiceModel);

        ArgumentCaptor<Doctor> argument = ArgumentCaptor.forClass(Doctor.class);
        Mockito.verify(doctorRepository).saveAndFlush(argument.capture());

        Doctor doctor = argument.getValue();
        assertNotNull(doctor);
    }

    @Test
    void findByUsername_whenDoctorHaveKey_shouldPast() {
        String username = "Dimitar";
        Doctor doctorBefore = new Doctor();
        doctorBefore.setUsername(username);

        Mockito.when(doctorRepository.findByUsername(username)).thenReturn(doctorBefore);

        DoctorServiceModel doctorAfter = doctorService.findByUsername(username);

        assertEquals(doctorBefore.getName(), doctorAfter.getName());
    }

    @Test
    void existsDoctorByUsername_whenDoctorExist_shouldPast() {
        String username = "Dimitar";
        Doctor doctorBefore = new Doctor();
        doctorBefore.setUsername(username);

        Mockito.when(doctorRepository.existsDoctorByUsername(username)).thenReturn(true);

        assertTrue(doctorService.existsDoctorByUsername(username));
    }

    @Test
    void findByUsernameAndPassword_whenAllIsGood_shouldPast() {
        String username = "Dimitar";
        String password = "11111q";
        Doctor doctorBefore = new Doctor();
        doctorBefore.setUsername(username);
        doctorBefore.setPassword(password);

        Mockito.when(doctorRepository.findByUsernameAndPassword(username, password)).thenReturn(doctorBefore);

        DoctorServiceModel doctorAfter = doctorService.findByUsernameAndPassword(username, password);

        assertEquals(doctorBefore.getName(), doctorAfter.getName());
    }
}