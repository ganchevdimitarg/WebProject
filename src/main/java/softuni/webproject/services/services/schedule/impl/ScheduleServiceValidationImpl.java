package softuni.webproject.services.services.schedule.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.data.repositories.ScheduleRepository;
import softuni.webproject.services.services.schedule.ScheduleServiceValidation;

import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ScheduleServiceValidationImpl implements ScheduleServiceValidation {
    private final static String DUPLICATE_DATE = "This date and time are saved";
    private final static String INCORRECT_DATE = "Incorrect date - look the placeholder";
    private final static String NO_SUCH_ANIMAL = "There is no such animal";
    private final static String NO_SUCH_DOCTOR = "There is no such doctor";

    private final ScheduleRepository scheduleRepository;

    @Override
    public void isValid(String dateReview, Doctor doctor, Animal animal) {
        if (scheduleRepository.findByDateReview(dateReview) != null){
            throw new IllegalArgumentException(DUPLICATE_DATE);
        }

        Pattern pattern = Pattern.compile("[0-9.: ]+");
        if (!pattern.matcher(dateReview).matches()) {
            throw new IllegalArgumentException(INCORRECT_DATE);
        }

        if (animal == null) {
            throw new NullPointerException(NO_SUCH_ANIMAL);
        }
        if (doctor == null) {
            throw new NullPointerException(NO_SUCH_DOCTOR);
        }
    }
}
