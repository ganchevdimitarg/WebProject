package softuni.webproject.services.services.schedule;

import softuni.webproject.services.models.ScheduleServiceModel;

import java.util.List;

public interface ScheduleService {
    void save(ScheduleServiceModel model);

    List<ScheduleServiceModel> getAll();
}
