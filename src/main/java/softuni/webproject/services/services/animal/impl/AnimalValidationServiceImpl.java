package softuni.webproject.services.services.animal.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import softuni.webproject.data.repositories.AnimalRepository;
import softuni.webproject.services.models.AnimalServiceModel;
import softuni.webproject.services.services.animal.AnimalValidationService;

@Service
@AllArgsConstructor
public class AnimalValidationServiceImpl implements AnimalValidationService {
    private final AnimalRepository animalRepository;

    @Override
    public boolean isValid(AnimalServiceModel animal) {
        return isBreedValid(animal) && isNameValid(animal) && isAgeValid(animal) && isNameFree(animal);
    }

    public boolean isBreedValid(AnimalServiceModel animalBreed){
        return !animalBreed.getBreed().isEmpty() && animalBreed.getBreed().length() >= 3 && animalBreed.getBreed().length() <= 10;
    }

    public boolean isNameValid(AnimalServiceModel animalName){
        return !animalName.getName().isEmpty() && animalName.getName().length() >= 3 && animalName.getName().length() <= 10;
    }

    public boolean isAgeValid(AnimalServiceModel animalAge){
        return animalAge.getAge() != 0 || animalAge.getAge() > 0;
    }

    private boolean isNameFree(AnimalServiceModel animal) {
        return animalRepository.findByName(animal.getName()) == null;
    }
}
