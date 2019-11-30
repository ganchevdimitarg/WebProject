package softuni.webproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.webproject.data.models.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {
}
