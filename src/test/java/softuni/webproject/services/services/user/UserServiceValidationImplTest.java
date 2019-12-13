package softuni.webproject.services.services.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import softuni.webproject.services.base.TestBase;
import softuni.webproject.services.models.UserServiceModel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class UserServiceValidationImplTest extends TestBase {

    @Autowired
    UserServiceValidation validation;

    @Test
    void isValid_whenAllIsValid_shouldThrowTrue() {
        UserServiceModel model = new UserServiceModel("Ivan", "ivan@abv.bg", "Varna",
                "0888888888", new ArrayList<>(), "");
        assertTrue(validation.isValid(model));
    }

    @Test
    void isValid_whenNameIsNotValid_shouldThrowFalse() {
        UserServiceModel model = new UserServiceModel("", "ivan@abv.bg", "Varna",
                "0888888888", new ArrayList<>(), "");
        assertFalse(validation.isValid(model));
    }

    @Test
    void isValid_whenEmailIsNotValid_shouldThrowFalse() {
        UserServiceModel model = new UserServiceModel("Ivan", "", "Varna",
                "0888888888", new ArrayList<>(), "");
        assertFalse(validation.isValid(model));
    }

    @Test
    void isValid_whenAddressIsNotValid_shouldThrowFalse() {
        UserServiceModel model = new UserServiceModel("Ivan", "ivan@abv.bg", "",
                "0888888888", new ArrayList<>(), "");
        assertFalse(validation.isValid(model));
    }

    @Test
    void isValid_whenPhoneNumberIsNotValid_shouldThrowFalse() {
        UserServiceModel model = new UserServiceModel("Ivan", "ivan@abv.bg", "Varna",
                "", new ArrayList<>(), "");
        assertFalse(validation.isValid(model));
    }
}