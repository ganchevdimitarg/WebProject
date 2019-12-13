package softuni.webproject.services.services.animal;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.Medicine;
import softuni.webproject.data.models.User;
import softuni.webproject.data.repositories.AnimalRepository;
import softuni.webproject.data.repositories.MedicineRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.errors.AnimalErrorHandlerException;
import softuni.webproject.errors.UserNotFoundException;
import softuni.webproject.services.base.TestBase;
import softuni.webproject.services.models.AnimalServiceModel;
import softuni.webproject.services.services.animal.impl.AnimalValidationServiceImpl;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AnimalServiceImplTest extends TestBase {
    @MockBean
    AnimalValidationServiceImpl validation;
    @MockBean
    AnimalRepository animalRepository;
    @MockBean
    UserRepository userRepository;
    @MockBean
    MedicineRepository medicineRepository;

    @Autowired
    AnimalService animalService;

    @Test
    void save_whenAnimalIsValidAndUserExist_shouldPast() {
        AnimalServiceModel animalModel = new AnimalServiceModel();
        animalModel.setBreed("dog");
        animalModel.setName("Max");
        animalModel.setAge(1);
        animalModel.setDisease("None");
        String userName = "ivan";
        User user = new User();
        user.setUsername(userName);

        Mockito.when(validation.isValid(animalModel)).thenReturn(true);
        Mockito.when(userRepository.findByUsername(userName)).thenReturn(user);

        animalService.save(animalModel, userName);

        ArgumentCaptor<Animal> argument = ArgumentCaptor.forClass(Animal.class);
        Mockito.verify(animalRepository).saveAndFlush(argument.capture());

        Animal animal = argument.getValue();
        assertNotNull(animal);
    }

    @Test
    void save_whenAnimalNotValidAndUserExist_shouldThrowException() {
        AnimalServiceModel animalModel = new AnimalServiceModel();
        animalModel.setBreed("dog");
        animalModel.setName("Max");
        animalModel.setAge(1);
        animalModel.setDisease("None");

        Mockito.when(validation.isValid(animalModel)).thenReturn(false);

        assertThrows(AnimalErrorHandlerException.class, () -> animalService.save(animalModel, ""));
    }

    @Test
    void findByName_whenAnimalNotExist_shouldThrowException() {
        String name = "Petko";

        Animal animal = this.creatAnimal();

        Mockito.when(animalRepository.findByName(animal.getName())).thenReturn(animal);

        assertThrows(AnimalErrorHandlerException.class, () -> animalService.findByName(""));
    }

    @Test
    void findByName_whenAnimalExist_shouldPast() {
        String name = "Petko";

        Animal animal = this.creatAnimal();

        Mockito.when(animalRepository.findByName(animal.getName())).thenReturn(animal);

        AnimalServiceModel animalServiceModel = animalService.findByName(name);

        assertEquals(animal.getName(), animalServiceModel.getName());

    }

    @Test
    void getCurrentUserAnimal_whenUserNotFound_shouldThrowException() {
        String name = "Petko";
        User user = new User();
        user.setUsername(name);

        Mockito.when(userRepository.findByUsername(name)).thenReturn(user);

        assertThrows(UserNotFoundException.class, () -> animalService.getCurrentUserAnimal(name));
    }

    @Test
    void addMedicineDisease_whenAnimalAndMedicineExists_shouldPast() throws IllegalAccessException {
        String animalName = "Petko";
        String medicineName = "Aloq";
        String disease = "Bad";
        Animal animal = this.creatAnimal();
        animal.setMedicines(new ArrayList<>());
        Medicine medicine = new Medicine();
        medicine.setName(medicineName);

        Mockito.when(animalRepository.findByName(animalName)).thenReturn(animal);
        Mockito.when(medicineRepository.findByName(medicineName)).thenReturn(medicine);

        animalService.addMedicineDisease(animalName, medicineName, disease);

        ArgumentCaptor<Animal> argument = ArgumentCaptor.forClass(Animal.class);
        Mockito.verify(animalRepository).saveAndFlush(argument.capture());

        Animal newAnimal = argument.getValue();
        assertNotNull(newAnimal);
    }

    @Test
    void addMedicineDisease_whenAnimalExistAndMedicineNotExist_shouldThrowException() throws NullPointerException {
        String animalName = "Petko";
        String disease = "Bad";
        Animal animal = this.creatAnimal();

        Mockito.when(animalRepository.findByName(animalName)).thenReturn(animal);
        Mockito.when(medicineRepository.findByName("medicineName")).thenReturn(null);

        assertThrows(NullPointerException.class, () -> animalService.addMedicineDisease(animalName, "", disease));
    }

    Animal creatAnimal() {
        Animal animal = new Animal();
        animal.setBreed("dog");
        animal.setName("Petko");
        animal.setAge(1);
        animal.setDisease("None");
        return animal;
    }
}