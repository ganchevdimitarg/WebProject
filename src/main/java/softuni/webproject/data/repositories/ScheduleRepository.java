package softuni.webproject.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.webproject.data.models.Schedule;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {

    @Query("select s from Schedule s")
    List<Schedule> getAll();

    @Transactional
    @Modifying
    void deleteScheduleByDateReview(String date);

    Schedule findByDateReview(String data);
}
