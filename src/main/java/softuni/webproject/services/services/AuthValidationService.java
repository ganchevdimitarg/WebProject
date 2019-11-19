package softuni.webproject.services.services;

import softuni.webproject.services.models.DoctorServiceModel;

public interface AuthValidationService {
    boolean isValid(DoctorServiceModel model);
}
