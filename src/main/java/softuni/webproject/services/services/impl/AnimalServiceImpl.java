package softuni.webproject.services.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.repositories.AnimalRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.services.models.AnimalServiceModel;
import softuni.webproject.services.services.AnimalService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AnimalServiceImpl(UserRepository userRepository, AnimalRepository animalRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.animalRepository = animalRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void save(AnimalServiceModel model, String  name) {
        Animal animal = modelMapper.map(model, Animal.class);
        animal.setUser(userRepository.findByName(name));

        animalRepository.saveAndFlush(animal);
    }

    @Override
    public AnimalServiceModel findByName(String name) {
        Optional<Animal> animalOptional = animalRepository.findByName(name);
        if (animalOptional.isEmpty()) {
            throw new NullPointerException("No such animal");
        }

        Animal animal = animalOptional.get();

        return modelMapper.map(animal, AnimalServiceModel.class);
    }

    @Override
    public List<AnimalServiceModel> getCurrentUserAnimal(String name){
        return userRepository.findByName(name)
                .getAnimals()
                .stream()
                .map(a -> modelMapper.map(a, AnimalServiceModel.class))
                .collect(Collectors.toList());
    }

}
