package softuni.webproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.webproject.data.models.IdentificationKey;

public interface IdentificationKeyRepository extends JpaRepository<IdentificationKey, String> {
    IdentificationKey findByLogKey(String key);
}
