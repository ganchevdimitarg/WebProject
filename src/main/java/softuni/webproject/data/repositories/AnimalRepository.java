package softuni.webproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.User;

import javax.transaction.Transactional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, String> {
    Animal findByName(String name);

    @Transactional
    @Modifying
    void deleteAnimalsByUser(User user);

}
