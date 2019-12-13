package softuni.webproject.services.services.doctor;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.webproject.data.models.IdentificationKey;
import softuni.webproject.data.repositories.IdentificationKeyRepository;
import softuni.webproject.services.base.TestBase;
import softuni.webproject.services.models.IdentificationKeyServiceModel;
import softuni.webproject.services.services.doctor.IdentificationKeyService;

import static org.junit.jupiter.api.Assertions.*;

class IdentificationKeyServiceImplTest extends TestBase {
    @MockBean
    IdentificationKeyRepository keyRepository;

    @Autowired
    IdentificationKeyService keyService;

    @Test
    void findByKeyName_whenKeyExist_shouldPast() {
        String keyName = "qqqqqq";
        IdentificationKey key = new IdentificationKey();
        key.setLogKey(keyName);

        Mockito.when(keyRepository.findByLogKey(keyName)).thenReturn(key);

       IdentificationKeyServiceModel keyModel =  keyService.findByKeyName(keyName);

       assertEquals(keyName, keyModel.getLogKey());
    }
}