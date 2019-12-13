package softuni.webproject.services.services.doctor;

import softuni.webproject.services.models.IdentificationKeyServiceModel;

public interface IdentificationKeyService {

    IdentificationKeyServiceModel findByKeyName(String keyName);

    void generateKey();

    void update(boolean state, String key);
}
