package softuni.webproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.webproject.data.models.User;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsUserByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    User findByName(String name);
}
