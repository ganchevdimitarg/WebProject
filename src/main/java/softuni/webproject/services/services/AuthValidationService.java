package softuni.webproject.services.services;

import softuni.webproject.services.models.BaseServiceModel;

public interface AuthValidationService {
    boolean isValid(BaseServiceModel model);
}
