package softuni.webproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.webproject.data.models.Medicine;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, String> {

    @Query("select m from Medicine m")
    List<Medicine> getAll();

    Medicine findByName(String name);
}
