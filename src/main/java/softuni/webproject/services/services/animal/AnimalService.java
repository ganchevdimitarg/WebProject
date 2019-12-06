package softuni.webproject.services.services.animal;

import softuni.webproject.services.models.AnimalServiceModel;

import java.util.List;

public interface AnimalService {
    void save(AnimalServiceModel model, String id);

    AnimalServiceModel findByName(String name);

    List<AnimalServiceModel> getCurrentUserAnimal(String name);

    void addMedicineDisease(String animalName, String medicineName, String disease);
}
