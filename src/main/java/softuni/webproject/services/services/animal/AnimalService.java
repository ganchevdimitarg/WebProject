package softuni.webproject.services.services.animal;

import softuni.webproject.services.models.AnimalServiceModel;

import java.util.List;

public interface AnimalService {
    void save(AnimalServiceModel model, String username);

    AnimalServiceModel findByName(String name);

    List<AnimalServiceModel> getCurrentUserAnimal(String name);

    void addMedicineDisease(String animalName, String medicineName, String disease) throws IllegalAccessException;

    List<AnimalServiceModel> getAll();
}
