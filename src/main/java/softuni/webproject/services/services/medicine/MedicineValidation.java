package softuni.webproject.services.services.medicine;

import softuni.webproject.services.models.MedicineServiceModel;

public interface MedicineValidation {
    boolean isValid(MedicineServiceModel model);
}
