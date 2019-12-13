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
    private final ScheduleRepository scheduleRepository;

    @Override
    public boolean isValid(String dateReview, Doctor doctor, Animal animal) {
        Pattern pattern = Pattern.compile("[0-9.: ]+");
        return scheduleRepository.findByDateReview(dateReview) == null &&
                pattern.matcher(dateReview).matches() &&
                animal != null &&
                doctor != null;
    }
}
