package softuni.webproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import softuni.webproject.data.models.User;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsUserByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update User set email= :email, address= :address, phoneNumber= :phoneNumber")
    void update(@Param("email") String email, @Param("address") String address, @Param("phoneNumber") String phoneNumber);

    @Transactional
    @Modifying
    void deleteByUsername(String username);
}
