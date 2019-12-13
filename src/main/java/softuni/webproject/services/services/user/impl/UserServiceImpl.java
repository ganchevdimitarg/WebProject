package softuni.webproject.services.services.user.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.User;
import softuni.webproject.data.repositories.AnimalRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.errors.UserNotFoundException;
import softuni.webproject.services.models.CurrentUser;
import softuni.webproject.services.models.UserServiceModel;
import softuni.webproject.services.services.user.UserService;
import softuni.webproject.services.services.user.UserServiceValidation;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final static String USER_NOT_FOUND = "Not such User";
    private final static String TRY_AGAIN = "Try again";

    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final UserServiceValidation validation;
    private final ModelMapper modelMapper;

    @Override
    public void save(UserServiceModel model) {
        if (!validation.isValid(model)) {
            throw new IllegalArgumentException(TRY_AGAIN);
        }
        userRepository.saveAndFlush(modelMapper.map(model, User.class));
    }

    @Override
    public void deleteUser(String username) {
        User user = getUser(username);
        List<Animal> animals = user.getAnimals();
        user.getAnimals().removeAll(animals);
        animalRepository.deleteAnimalsByUser(user);
        userRepository.deleteByUsername(user.getUsername());
    }

    @Override
    public void update(UserServiceModel model) {
        try {
            userRepository.update(model.getEmail(), model.getAddress(), model.getPhoneNumber());
        } catch (Exception e) {
            throw new IllegalArgumentException(TRY_AGAIN);
        }
    }

    @Override
    public void deletePet(String name, CurrentUser currentUser) {
        Animal animal = animalRepository.findByName(name);
        User user = getUser(currentUser.getUsername());
        user.getAnimals().remove(animal);
        animalRepository.delete(animal);
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return modelMapper.map(userRepository.findByUsernameAndPassword(username, password), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return modelMapper.map(userRepository.findByUsername(username), UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> getAll() {
        return userRepository.getAll()
                .stream()
                .map(u -> modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    private User getUser(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
    }


}
