package softuni.webproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.webproject.data.models.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    boolean findByUsername(String name);
}
