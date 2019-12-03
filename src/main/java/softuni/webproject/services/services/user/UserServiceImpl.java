package softuni.webproject.services.services.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.User;
import softuni.webproject.data.repositories.AnimalRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.services.models.UserServiceModel;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AnimalRepository animalRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.animalRepository = animalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel findByUsername(String name) {
        return modelMapper.map(userRepository.findByUsername(name), UserServiceModel.class);
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        List<Animal> animals = user.getAnimals();
        user.getAnimals().removeAll(animals);
        animalRepository.deleteAnimalsByUser(user);
        userRepository.deleteByUsername(user.getUsername());
    }

    @Override
    public void update(UserServiceModel model) {
        User user = modelMapper.map(model, User.class);
        userRepository.update(user.getEmail(), user.getAddress(), user.getPhoneNumber());
    }

//    TODO
//    @Override
//    public void deletePet(String username, String name) {
//        User user = userRepository.findByUsername(username);
//        List<Animal> animals = user.getAnimals();
//        Animal animal = animalRepository.findByName(name).orElseThrow();
//        animals.remove(animal);
//    }
}
