package softuni.webproject.services.services.animal;

import softuni.webproject.services.models.AnimalServiceModel;

public interface AnimalValidationService {
    boolean isValid(AnimalServiceModel animal);
}
