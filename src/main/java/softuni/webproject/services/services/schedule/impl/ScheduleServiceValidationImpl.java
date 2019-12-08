package softuni.webproject.services.services.schedule.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.services.services.schedule.ScheduleServiceValidation;

import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ScheduleServiceValidationImpl implements ScheduleServiceValidation {

    @Override
    public void isValid(String dateReview, Doctor doctor, Animal animal) {
        Pattern pattern = Pattern.compile("[0-9.: ]+");
        if (!pattern.matcher(dateReview).matches()) {
            throw new IllegalArgumentException("Incorrect date - look the placeholder");
        }
        if (animal == null) {
            throw new NullPointerException("There is no such animal");
        }
        if (doctor == null) {
            throw new NullPointerException("There is no such doctor");
        }
    }
}
