package softuni.webproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleServiceModel {
    private String dateReview;
    private String doctor;
    private String animal;
    private boolean isFinished;

}
