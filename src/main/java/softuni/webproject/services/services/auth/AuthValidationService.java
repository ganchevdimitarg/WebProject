package softuni.webproject.services.services.auth;

import softuni.webproject.services.models.BaseServiceModel;

public interface AuthValidationService {
    boolean isValid(BaseServiceModel model) throws IllegalArgumentException;
}
