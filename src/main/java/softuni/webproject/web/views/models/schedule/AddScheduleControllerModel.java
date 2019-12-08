package softuni.webproject.web.views.models.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class AddScheduleControllerModel {
    @Column(nullable = false, unique = true)
    @NotNull
    @NotEmpty
    @Pattern(regexp = "[0-9.: ]+", message = "Incorrect date")
    private String dateReview;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String doctor;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String animal;
    private boolean isFinished;
}
