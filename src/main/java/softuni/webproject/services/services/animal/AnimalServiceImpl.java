package softuni.webproject.services.services.animal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.Medicine;
import softuni.webproject.data.repositories.AnimalRepository;
import softuni.webproject.data.repositories.MedicineRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.services.models.AnimalServiceModel;
import softuni.webproject.services.services.medicine.MedicineService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final MedicineService medicineService;
    private final MedicineRepository medicineRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AnimalServiceImpl(UserRepository userRepository, AnimalRepository animalRepository, MedicineService medicineService, MedicineRepository medicineRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.animalRepository = animalRepository;
        this.medicineService = medicineService;
        this.medicineRepository = medicineRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(AnimalServiceModel model, String  name) {
        Animal animal = modelMapper.map(model, Animal.class);
        animal.setUser(userRepository.findByUsername(name));

        animalRepository.saveAndFlush(animal);
    }

    @Override
    public AnimalServiceModel findByName(String name) {
        Animal animal = animalRepository.findByName(name);
        if (animal == null) {
            throw new NullPointerException("No such animal");
        }

        return modelMapper.map(animal, AnimalServiceModel.class);
    }

    @Override
    public List<AnimalServiceModel> getCurrentUserAnimal(String name){
        return userRepository.findByUsername(name)
                .getAnimals()
                .stream()
                .map(a -> modelMapper.map(a, AnimalServiceModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public void addMedicineDisease(String animalName, String medicineName, String disease) {
        Animal animal = animalRepository.findByName(animalName);
//        MedicineServiceModel medicine = medicineService.findByName(medicineName);
        Medicine medicine = medicineRepository.findByName(medicineName);
        medicine.getAnimals().add(animal);
        animal.setDisease(disease);
//        animal.getMedicines().add(modelMapper.map(medicine, Medicine.class));
        medicineRepository.saveAndFlush(medicine);
        animalRepository.saveAndFlush(animal);
    }

}
