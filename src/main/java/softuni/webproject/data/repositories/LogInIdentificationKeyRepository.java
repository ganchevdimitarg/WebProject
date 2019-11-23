package softuni.webproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.webproject.data.models.LogInIdentificationKey;

public interface LogInIdentificationKeyRepository extends JpaRepository<LogInIdentificationKey, String> {
    LogInIdentificationKey findByLogKey(String key);
}
