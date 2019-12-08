package softuni.webproject.services.services.doctor;

import softuni.webproject.services.models.IdentificationKeyServiceModel;

public interface IdentificationKeyService {
    void save(IdentificationKeyServiceModel key);

    IdentificationKeyServiceModel findByKeyName(String keyName);

    void generateKey();
}
