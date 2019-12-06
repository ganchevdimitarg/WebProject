package softuni.webproject.web.views.models.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleViewModel {
    private String dateReview;
    private String doctor;
    private String animal;
    private boolean isFinished;
}
