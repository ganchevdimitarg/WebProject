package softuni.webproject.services.services.schedule;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.data.models.Schedule;
import softuni.webproject.data.repositories.AnimalRepository;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.data.repositories.ScheduleRepository;
import softuni.webproject.services.base.TestBase;
import softuni.webproject.services.models.ScheduleServiceModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleServiceImplTest extends TestBase {

    @MockBean
    DoctorRepository doctorRepository;
    @MockBean
    AnimalRepository animalRepository;
    @MockBean
    ScheduleRepository scheduleRepository;
    @MockBean
    ScheduleServiceValidation validation;

    @Autowired
    ScheduleService scheduleService;

    @Test
    void save_whenAllIsValid_shouldPast() {
        String date = "16.03.2019";
        String doctorName = "Max";
        String animalName = "Nino";
        Doctor doctor = new Doctor();
        doctor.setName(doctorName);
        Animal animal = new Animal();
        animal.setName(animalName);
        ScheduleServiceModel serviceModel = new ScheduleServiceModel(date, doctorName, animalName);
        Mockito.when(doctorRepository.findByName(doctorName)).thenReturn(doctor);
        Mockito.when(animalRepository.findByName(animalName)).thenReturn(animal);
        Mockito.when(validation.isValid(date,doctor,animal)).thenReturn(true);
        animal.setDoctor(doctor);

        scheduleService.save(serviceModel);

        ArgumentCaptor<Schedule> argument = ArgumentCaptor.forClass(Schedule.class);
        Mockito.verify(scheduleRepository).saveAndFlush(argument.capture());

        Schedule schedule = argument.getValue();
        assertNotNull(schedule);
    }

    @Test
    void getAll_whenScheduleHaveDate_shouldPast() {
        String date = "16.03.2019";
        String doctorName = "Max";
        String animalName = "Nino";

        Doctor doctor = new Doctor();
        doctor.setName(doctorName);
        Animal animal = new Animal();
        animal.setName(animalName);

        List<Schedule> schedules = new ArrayList<>();
        Schedule schedule = new Schedule(date, doctor, animal);
        schedules.add(schedule);

        Mockito.when(doctorRepository.findByName(doctorName)).thenReturn(doctor);
        Mockito.when(animalRepository.findByName(animalName)).thenReturn(animal);
        Mockito.when(scheduleRepository.getAll()).thenReturn(schedules);

        List<ScheduleServiceModel> scheduleServiceModel = scheduleService.getAll();

        assertEquals(schedules.size(), scheduleServiceModel.size());
    }

    @Test
    void deleteScheduleByDateReview() {
//        String date = "16.03.2019";
//        Doctor doctor = new Doctor();
//        doctor.setName("Ivan");
//        Animal animal = new Animal();
//        animal.setName("Penka");
//        Schedule schedule = new Schedule(date, doctor, animal);
//
//        scheduleService.deleteScheduleByDateReview(date);
//
//        int afterDelete = scheduleService.getAll().size();
//
//        assertEquals(0, afterDelete);

    }
}