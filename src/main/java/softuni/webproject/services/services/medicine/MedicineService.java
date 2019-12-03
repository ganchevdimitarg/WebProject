package softuni.webproject.services.services.medicine;

import softuni.webproject.services.models.MedicineServiceModel;

import java.util.List;

public interface MedicineService {
    void save (MedicineServiceModel model);

    List<MedicineServiceModel> getAll();

    MedicineServiceModel findByName(String name);
}
