package softuni.webproject.services.services.doctor.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.data.models.IdentificationKey;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.data.repositories.IdentificationKeyRepository;
import softuni.webproject.services.models.DoctorServiceModel;
import softuni.webproject.services.services.auth.HashingService;
import softuni.webproject.services.services.doctor.DoctorService;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final IdentificationKeyRepository keyRepository;
    private final HashingService hashingService;
    private final ModelMapper modelMapper;

    @Override
    public void save(DoctorServiceModel model){
        IdentificationKey key = keyRepository.findByLogKey(model.getLogInKey());
        if (key != null) {
            Doctor doctor = modelMapper.map(model, Doctor.class);
            doctor.setPassword(hashingService.hash(doctor.getPassword()));
            doctor.setLogInKey(modelMapper.map(key, IdentificationKey.class));
            doctor.setAnimals(new ArrayList<>());
            doctor.setSchedules(new ArrayList<>());
            keyRepository.update(false, model.getLogInKey());
            doctorRepository.saveAndFlush(doctor);
        }
    }

    @Override
    public DoctorServiceModel findByUsername(String username) {
        return modelMapper.map(doctorRepository.findByUsername(username), DoctorServiceModel.class);
    }

    @Override
    public boolean existsDoctorByUsername(String username) {
        return doctorRepository.existsDoctorByUsername(username);
    }

    @Override
    public DoctorServiceModel findByUsernameAndPassword(String username, String password) {
        return modelMapper.map(doctorRepository.findByUsernameAndPassword(username, password), DoctorServiceModel.class);
    }

    @Override
    public DoctorServiceModel findByName(String name) {
        return modelMapper.map(doctorRepository.findByUsername(name), DoctorServiceModel.class);
    }
}
