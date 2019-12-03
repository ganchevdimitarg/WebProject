package softuni.webproject.services.services.doctor;

import softuni.webproject.services.models.DoctorServiceModel;

public interface DoctorService {
    DoctorServiceModel findByUsername(String username);
}
