package softuni.webproject.services.services.medicine;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Medicine;
import softuni.webproject.data.repositories.MedicineRepository;
import softuni.webproject.services.models.MedicineServiceModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;
    private final ModelMapper modelMapper;

    @Override
    public void save(MedicineServiceModel model) {
        medicineRepository.saveAndFlush(modelMapper.map(model, Medicine.class));
    }

    @Override
    public List<MedicineServiceModel> getAll() {
        return medicineRepository.findAll()
                .stream()
                .map(m -> modelMapper.map(m, MedicineServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public MedicineServiceModel findByName(String name) {
        return modelMapper.map(medicineRepository.findByName(name), MedicineServiceModel.class);
    }
}
