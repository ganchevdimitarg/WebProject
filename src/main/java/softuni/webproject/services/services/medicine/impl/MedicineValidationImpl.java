package softuni.webproject.services.services.medicine.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import softuni.webproject.data.repositories.MedicineRepository;
import softuni.webproject.services.models.MedicineServiceModel;
import softuni.webproject.services.services.medicine.MedicineValidation;

@Service
@AllArgsConstructor
public class MedicineValidationImpl implements MedicineValidation {
    private final MedicineRepository medicineRepository;

    @Override
    public boolean isValid(MedicineServiceModel model) {
        return isNameValid(model.getName()) && isDescriptionValid(model.getDescription());
    }

    private boolean isDescriptionValid(String description) {
        return description != null;
    }

    private boolean isNameValid(String name) {
        return name != null && medicineRepository.findByName(name) == null;
    }
}
