package softuni.webproject.services.services.schedule;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.data.models.Schedule;
import softuni.webproject.data.repositories.ScheduleRepository;
import softuni.webproject.services.base.TestBase;
import static org.junit.jupiter.api.Assertions.*;

class ScheduleServiceValidationImplTest extends TestBase {
    @MockBean
    ScheduleRepository scheduleRepository;

    @Autowired
    ScheduleServiceValidation validation;

    @Test
    void isValid_whenDateIsNotFree_shouldFalse() {
        String date = "16.03.2019";
        Schedule schedule = new Schedule();
        schedule.setDateReview(date);

        Mockito.when(scheduleRepository.findByDateReview(date)).thenReturn(schedule);

        assertFalse(validation.isValid(date, new Doctor(), new Animal()));
    }

    @Test
    void isValid_whenDateIsFreeAndDoctorIsNull_shouldFalse() {
        String date = "16.03.2019";
        Schedule schedule = new Schedule();
        schedule.setDateReview(date);

        Mockito.when(scheduleRepository.findByDateReview(date)).thenReturn(schedule);

        assertFalse(validation.isValid(date, null, new Animal()));
    }

    @Test
    void isValid_whenDateIsFreeAndDoctorIsNotNullAndAnimalIsNull_shouldFalse() {
        String date = "16.03.2019";
        Schedule schedule = new Schedule();
        schedule.setDateReview(date);

        Mockito.when(scheduleRepository.findByDateReview(date)).thenReturn(schedule);

        assertFalse(validation.isValid(date, new Doctor(),null));
    }

    @Test
    void isValid_whenDateIsFreeAndDoctorIsNotNullAndAnimalIsNotNull_shouldTrue() {
        String date = "16.03.2019";
        Schedule schedule = new Schedule();
        schedule.setDateReview(date);

        Mockito.when(scheduleRepository.findByDateReview(date)).thenReturn(null);

        assertTrue(validation.isValid(date, new Doctor(),new Animal()));
    }
}