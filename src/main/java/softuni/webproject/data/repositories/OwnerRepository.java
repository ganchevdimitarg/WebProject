package softuni.webproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.webproject.data.entities.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, String> {
    Owner findOwnerByUsernameAndPassword(String username, String password);

}
