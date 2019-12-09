package softuni.webproject.services.services.schedule;

import org.springframework.data.jpa.repository.Modifying;
import softuni.webproject.services.models.ScheduleServiceModel;

import javax.transaction.Transactional;
import java.util.List;

public interface ScheduleService {
    void save(ScheduleServiceModel model);

    List<ScheduleServiceModel> getAll();

    @Transactional
    @Modifying
    void deleteScheduleByDateReview(String date);

    ScheduleServiceModel findByData(String data);
}
