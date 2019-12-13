package softuni.webproject.services.services.medicine;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.webproject.data.models.Medicine;
import softuni.webproject.data.repositories.MedicineRepository;
import softuni.webproject.services.base.TestBase;
import softuni.webproject.services.models.MedicineServiceModel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MedicineServiceImplTest extends TestBase {
    @MockBean
    MedicineRepository medicineRepository;
    @Autowired
    MedicineService medicineService;

    @Test
    void save_whenModelIsValid_shouldPast() {
        MedicineServiceModel model = new MedicineServiceModel("Avrora", "The best", "");
        medicineService.save(model);

        ArgumentCaptor<Medicine> argument = ArgumentCaptor.forClass(Medicine.class);
        Mockito.verify(medicineRepository).saveAndFlush(argument.capture());

        Medicine medicine = argument.getValue();
        assertNotNull(medicine);
    }

    @Test
    void findByName_whenMedicinNameIsValid_shouldPast() {
        String name = "Avrora";

        Medicine medicine = new Medicine();
        medicine.setName(name);

        Mockito.when(medicineRepository.findByName(name)).thenReturn(medicine);

        MedicineServiceModel model = medicineService.findByName(name);

        assertEquals(medicine.getName(), model.getName());
    }

    Medicine createMedicines(){
        return new Medicine("Avrora", "The best", new ArrayList<>(), "");
    }
}