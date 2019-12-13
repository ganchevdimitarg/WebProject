package softuni.webproject.services.services.user.impl;

import org.springframework.stereotype.Service;
import softuni.webproject.services.models.UserServiceModel;
import softuni.webproject.services.services.user.UserServiceValidation;

@Service
public class UserServiceValidationImpl implements UserServiceValidation {

    @Override
    public boolean isValid(UserServiceModel model) {
        return isNameValid(model.getName()) &&
                isEmailValid(model.getEmail()) &&
                isAddressValid(model.getAddress()) &&
                isPhoneNameValid(model.getPhoneNumber());
    }

    private boolean isPhoneNameValid(String phoneNumber) {
        return !phoneNumber.isEmpty();
    }

    private boolean isAddressValid(String address) {
        return !address.isEmpty();
    }

    private boolean isEmailValid(String email) {
        return !email.isEmpty();
    }

    private boolean isNameValid(String name) {
        return (name.length() >= 3 && name.length() <= 20);
    }
}
