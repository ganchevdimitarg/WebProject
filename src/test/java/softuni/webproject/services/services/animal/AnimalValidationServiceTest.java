package softuni.webproject.services.services.animal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.repositories.AnimalRepository;
import softuni.webproject.services.base.TestBase;
import softuni.webproject.services.models.AnimalServiceModel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnimalValidationServiceTest extends TestBase {
    @MockBean
    AnimalRepository animalRepository;

    @Autowired
    AnimalValidationService validation;

    @Test
    void isValid_whenAnimalIsValid_shouldPast() {
        AnimalServiceModel animal = new AnimalServiceModel("dog", "Max", 1, null, "", "", new ArrayList<>());
        boolean isValid = validation.isValid(animal);
        assertTrue(isValid);
    }

    @Test
    void isValid_whenAnimalBreedNotValid_shouldFalse() {
        AnimalServiceModel animal = new AnimalServiceModel("", "Max", 1, null, "", "", new ArrayList<>());
        boolean isValid = validation.isValid(animal);
        assertFalse(isValid);
    }

    @Test
    void isValid_whenAnimalNameNotValid_shouldFalse() {
        AnimalServiceModel animal = new AnimalServiceModel("dog", "", 1, null, "", "", new ArrayList<>());
        boolean isValid = validation.isValid(animal);
        assertFalse(isValid);
    }

    @Test
    void isValid_whenAnimalAgeNotValid_shouldFalse() {
        AnimalServiceModel animal = new AnimalServiceModel("dog", "Max", 0, null, "", "", new ArrayList<>());
        boolean isValid = validation.isValid(animal);
        assertFalse(isValid);
    }

    @Test
    void isValid_whenAnimalNameIsBusy_shouldFalse() {
        AnimalServiceModel animal1 = new AnimalServiceModel("dog", "Max", 1, null, "", "", new ArrayList<>());
        Animal animal2 = new Animal();
        animal2.setName("Max");
        Mockito.when(animalRepository.findByName(animal2.getName())).thenReturn(animal2);
        boolean isValid = validation.isValid(animal1);
        assertFalse(isValid);
    }
}