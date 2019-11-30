package softuni.webproject.services.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.User;
import softuni.webproject.data.repositories.AnimalRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.services.models.UserRegisterServiceModel;
import softuni.webproject.services.services.UserService;

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
    public UserRegisterServiceModel findByName(String name) {
        return modelMapper.map(userRepository.findByName(name), UserRegisterServiceModel.class);
    }

//    TODO
    @Override
    public void deleteUser(String name) {
        User user = userRepository.findByName(name);
        List<Animal> animals = user.getAnimals();
        user.getAnimals().removeAll(animals);
        userRepository.delete(user);
    }
}
