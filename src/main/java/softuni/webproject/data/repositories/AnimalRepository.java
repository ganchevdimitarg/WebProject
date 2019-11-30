package softuni.webproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.webproject.data.models.Animal;

import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, String> {
    Optional<Animal> findByName(String name);

}
