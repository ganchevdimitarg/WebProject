package softuni.webproject.services.services.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.Doctor;
import softuni.webproject.data.models.Schedule;
import softuni.webproject.data.repositories.AnimalRepository;
import softuni.webproject.data.repositories.DoctorRepository;
import softuni.webproject.data.repositories.ScheduleRepository;
import softuni.webproject.services.models.ScheduleServiceModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(DoctorRepository doctorRepository, AnimalRepository animalRepository,
                               ScheduleRepository scheduleRepository) {
        this.doctorRepository = doctorRepository;
        this.animalRepository = animalRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void save(ScheduleServiceModel model) {
        Doctor doctor = doctorRepository.findByName(model.getDoctor());
        if (doctor == null){
           throw new NullPointerException("There is no such doctor");
        }
        Animal animal = animalRepository.findByName(model.getAnimal());
        if (animal == null){
            throw new NullPointerException("There is no such animal");
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

}
