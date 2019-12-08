package softuni.webproject.services.services.user;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.User;
import softuni.webproject.data.repositories.AnimalRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.errors.UserNotFoundException;
import softuni.webproject.services.models.CurrentUser;
import softuni.webproject.services.models.UserServiceModel;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final ModelMapper modelMapper;

    @Override
    public void save(UserServiceModel model) {
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
            throw new IllegalArgumentException("Try again");
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
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return modelMapper.map(userRepository.findByUsernameAndPassword(username, password), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return modelMapper.map(userRepository.findByUsername(username), UserServiceModel.class);
    }

    private User getUser(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            throw new UserNotFoundException("Not such User");
        }
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleException(UserNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }
}
