package softuni.webproject.services.services.animal.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.Medicine;
import softuni.webproject.data.repositories.AnimalRepository;
import softuni.webproject.data.repositories.MedicineRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.errors.AnimalErrorHandlerException;
import softuni.webproject.errors.UserNotFoundException;
import softuni.webproject.services.models.AnimalServiceModel;
import softuni.webproject.services.services.animal.AnimalService;
import softuni.webproject.services.services.animal.AnimalValidationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final static String INCORRECTLY_ENTERED_DATA = "Incorrectly entered data! Please try again! OR Name is not available!";
    private final static String NOT_FOUNT_ANIMAL = "No such animal";
    private final static String NOT_FOUNT_USER = "No such user";

    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final MedicineRepository medicineRepository;
    private final AnimalValidationService validationService;
    private final ModelMapper modelMapper;

    @Override
    public void save(AnimalServiceModel model, String username) {
        if (!validationService.isValid(model)){
            throw new AnimalErrorHandlerException(INCORRECTLY_ENTERED_DATA);
        }
        Animal animal = modelMapper.map(model, Animal.class);

        try {
            animal.setUser(userRepository.findByUsername(username));
        } catch (Exception e){
            throw  new UserNotFoundException(NOT_FOUNT_USER);
        }
        animalRepository.saveAndFlush(animal);
    }

    @Override
    public AnimalServiceModel findByName(String name) {
        Animal animal = animalRepository.findByName(name);
        if (animal == null) {
            throw new AnimalErrorHandlerException(NOT_FOUNT_ANIMAL);
        }

        return modelMapper.map(animal, AnimalServiceModel.class);
    }

    @Override
    public List<AnimalServiceModel> getCurrentUserAnimal(String name){
        try {
            return userRepository.findByUsername(name)
                    .getAnimals()
                    .stream()
                    .map(a -> modelMapper.map(a, AnimalServiceModel.class))
                    .collect(Collectors.toList());
        } catch (Exception e){
            throw new UserNotFoundException(NOT_FOUNT_USER);
        }
    }

    @Override
    public void addMedicineDisease(String animalName, String medicineName, String disease) throws IllegalAccessException {
        Animal animal = animalRepository.findByName(animalName);
        Medicine medicine;
        try {
            medicine = medicineRepository.findByName(medicineName);
        } catch (Exception e){
           throw new IllegalAccessException("No such medicine");
        }
        animal.getMedicines().add(medicine);
        animal.setDisease(disease);
        animalRepository.saveAndFlush(animal);
    }

}
