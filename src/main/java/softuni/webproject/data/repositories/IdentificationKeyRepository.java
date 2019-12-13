package softuni.webproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import softuni.webproject.data.models.IdentificationKey;

import javax.transaction.Transactional;

public interface IdentificationKeyRepository extends JpaRepository<IdentificationKey, String> {
    IdentificationKey findByLogKey(String key);

    @Transactional
    @Modifying
    @Query("update IdentificationKey set isFree= :state where logKey= :key")
    void update(@Param("state") boolean state, @Param("key") String key);
}
