package softuni.webproject.services.services.schedule;

import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.Doctor;

public interface ScheduleServiceValidation {
    boolean isValid(String dateReview, Doctor doctor, Animal animal);
}
