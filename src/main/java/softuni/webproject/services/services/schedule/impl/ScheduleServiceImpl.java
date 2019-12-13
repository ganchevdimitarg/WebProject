package softuni.webproject.services.services.schedule.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.data.models.Schedule;
import softuni.webproject.data.repositories.AnimalRepository;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.data.repositories.ScheduleRepository;
import softuni.webproject.services.models.ScheduleServiceModel;
import softuni.webproject.services.services.schedule.ScheduleService;
import softuni.webproject.services.services.schedule.ScheduleServiceValidation;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final static String TRY_AGAIN = "Try again";

    private final ScheduleRepository scheduleRepository;
    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;
    private final ScheduleServiceValidation validation;
    private final ModelMapper modelMapper;

    @Override
    public void save(ScheduleServiceModel model) {
        Doctor doctor = doctorRepository.findByName(model.getDoctor());
        Animal animal = animalRepository.findByName(model.getAnimal());
        if(!validation.isValid(model.getDateReview(), doctor, animal)){
            throw new IllegalArgumentException(TRY_AGAIN);
        }
        animal.setDoctor(doctor);

        Schedule schedule = new Schedule();
        schedule.setDateReview(model.getDateReview());
        schedule.setDoctor(doctor);
        schedule.setAnimal(animal);

        animalRepository.saveAndFlush(animal);
        scheduleRepository.saveAndFlush(schedule);
    }

    @Override
    public List<ScheduleServiceModel> getAll() {
        return scheduleRepository.getAll()
                .stream()
                .map(s -> {
                    ScheduleServiceModel sch = new ScheduleServiceModel();
                    sch.setDateReview(s.getDateReview());
                    sch.setAnimal(animalRepository.findByName(s.getAnimal().getName()).getName());
                    sch.setDoctor(doctorRepository.findByName(s.getDoctor().getName()).getName());
                    return sch;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteScheduleByDateReview(String date) {
        scheduleRepository.deleteScheduleByDateReview(date);
    }

    @Override
    public ScheduleServiceModel findByData(String data) {
        return modelMapper.map(scheduleRepository.findByDateReview(data), ScheduleServiceModel.class);
    }

}
