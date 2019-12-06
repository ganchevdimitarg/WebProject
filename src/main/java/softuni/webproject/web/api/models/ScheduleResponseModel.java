package softuni.webproject.web.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponseModel {
    private String dateReview;
    private String doctor;
    private String animal;
    private boolean isFinished;
}
