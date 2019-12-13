package softuni.webproject.services.services.user;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.webproject.data.models.User;
import softuni.webproject.data.repositories.AnimalRepository;
import softuni.webproject.data.repositories.UserRepository;
import softuni.webproject.services.base.TestBase;
import softuni.webproject.services.models.UserServiceModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest extends TestBase {
    @MockBean
    UserRepository userRepository;
    @MockBean
    AnimalRepository animalRepository;
    @MockBean
    UserServiceValidation validation;

    @Autowired
    UserService userService;

    @Test
    void save() {
        UserServiceModel userModel = new UserServiceModel("Dimitar", "abc@abv.bg", "", "", new ArrayList<>(),"");
        Mockito.when(validation.isValid(userModel)).thenReturn(true);
        userService.save(userModel);

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).saveAndFlush(argument.capture());

        User user = argument.getValue();
        assertNotNull(user);
    }

    @Test
    void findByUsernameAndPassword_whenAllIsGood_shouldPast() {
        String username = "Dimitar";
        String password = "11111q";
        User userBefore = new User();
        userBefore.setUsername(username);
        userBefore.setPassword(password);

        Mockito.when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(userBefore);

        UserServiceModel userAfter = userService.findByUsernameAndPassword(username, password);

        assertEquals(userBefore.getName(), userAfter.getName());
    }

    @Test
    void findByUsername_whenUserHaveUsername_shouldPast() {
        String username = "Dimitar";
        User userBefore = new User();
        userBefore.setUsername(username);

        Mockito.when(userRepository.findByUsername(username)).thenReturn(userBefore);

        UserServiceModel userAfter = userService.findByUsername(username);

        assertEquals(userBefore.getName(), userAfter.getName());
    }

    @Test
    void existsUserByUsername_whenUSerExist_shouldReturnTrue(){
        String username = "Dimitar";
        User user = new User();
        user.setUsername(username);

        Mockito.when(userRepository.existsUserByUsername(username)).thenReturn(true);

        assertTrue(userService.existsUserByUsername(username));
    }

    @Test
    void getAll_whenUserIsValid_shouldPast(){
        User user = new User();
        user.setUsername("name");
        user.setPassword("11111q");
        user.setName("Petko");
        user.setAddress("Varna");
        user.setPhoneNumber("0888888888");

        List<User> users = new ArrayList<>(List.of(user));

        Mockito.when(userRepository.getAll()).thenReturn(users);

        List<UserServiceModel> userServiceModels = userService.getAll();

        assertEquals(users.size(), userServiceModels.size());
    }
}