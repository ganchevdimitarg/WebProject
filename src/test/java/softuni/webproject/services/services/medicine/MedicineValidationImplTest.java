package softuni.webproject.services.services.medicine;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.webproject.data.models.Medicine;
import softuni.webproject.data.repositories.MedicineRepository;
import softuni.webproject.services.base.TestBase;
import softuni.webproject.services.models.MedicineServiceModel;

import static org.junit.jupiter.api.Assertions.*;

class MedicineValidationImplTest extends TestBase {
    @MockBean
    MedicineRepository medicineRepository;

    @Autowired
    MedicineValidation validation;

    @Test
    void isValid_whenNameIsValidAndDescriptionIsNull_shouldThrowFalse() {
        MedicineServiceModel model = new MedicineServiceModel("Avrora", null, "");

        assertFalse(validation.isValid(model));
    }

    @Test
    void isValid_whenNameIsNotValidAndDescriptionIsValid_shouldThrowFalse() {
        MedicineServiceModel model = new MedicineServiceModel(null, "dsddas", "");

        assertFalse(validation.isValid(model));
    }

    @Test
    void isValid_whenNameExistAndDescriptionIsValid_shouldThrowFalse() {
        String name = "Avrora";
        MedicineServiceModel model = new MedicineServiceModel(name, "dsddas", "");
        Medicine medicine = new Medicine();
        medicine.setName(name);
        Mockito.when(medicineRepository.findByName(name)).thenReturn(medicine);

        assertFalse(validation.isValid(model));
    }

    @Test
    void isValid_whenNameIsValidAndNameIsFreeAndDescriptionIsValid_shouldThrowFalse() {
        String name = "Avrora";
        MedicineServiceModel model = new MedicineServiceModel(name, "dsddas", "");

        assertTrue(validation.isValid(model));
    }
}