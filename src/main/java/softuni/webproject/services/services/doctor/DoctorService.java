package softuni.webproject.services.services.doctor;

import softuni.webproject.services.models.DoctorServiceModel;

public interface DoctorService {
    void createDoctor(DoctorServiceModel doctor);

    DoctorServiceModel findByUsername(String username);

    boolean existsDoctorByUsername(String username);

    DoctorServiceModel findByUsernameAndPassword(String username, String password);

    DoctorServiceModel findByName(String name);


}
