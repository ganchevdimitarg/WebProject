package softuni.webproject.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.webproject.services.models.CurrentUser;
import softuni.webproject.services.models.MedicineServiceModel;
import softuni.webproject.services.models.ScheduleServiceModel;
import softuni.webproject.services.services.animal.AnimalService;
import softuni.webproject.services.services.doctor.DoctorService;
import softuni.webproject.services.services.medicine.MedicineService;
import softuni.webproject.services.services.schedule.ScheduleService;
import softuni.webproject.web.models.doctor.DoctorViewModel;
import softuni.webproject.web.models.medicine.AddMedicineControlModel;
import softuni.webproject.web.models.medicine.MedicineViewModel;
import softuni.webproject.web.models.schedule.AddScheduleControllerModel;
import softuni.webproject.web.models.schedule.ScheduleViewModel;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    private final AnimalService animalService;
    private final MedicineService medicineService;
    private final ScheduleService scheduleService;
    private final ModelMapper modelMapper;

    @Autowired
    public DoctorController(DoctorService doctorService, AnimalService animalService, MedicineService medicineService, ScheduleService scheduleService, ModelMapper modelMapper) {
        this.doctorService = doctorService;
        this.animalService = animalService;
        this.medicineService = medicineService;
        this.scheduleService = scheduleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/doctor-home")
    public ModelAndView getDoctorHome(ModelAndView modelAndView, HttpSession session) {
        CurrentUser currentUser = getCurrentUser(session);
        DoctorViewModel doctorViewModel = modelMapper.map(doctorService.findByUsername(currentUser.getUsername()), DoctorViewModel.class);
        modelAndView.addObject("doctor", doctorViewModel);
        modelAndView.setViewName("doctor/doctor-home");
        return modelAndView;
    }

    @GetMapping("/medicine")
    public ModelAndView getMedicine(ModelAndView modelAndView) {
        List<MedicineViewModel> model = medicineService.getAll()
                .stream()
                .map(m -> modelMapper.map(m, MedicineViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("medicines", model);
        modelAndView.setViewName("doctor/medicine");
        return modelAndView;
    }

    @ModelAttribute("addNewMedicine")
    public AddMedicineControlModel addNewMedicine() {
        return new AddMedicineControlModel();
    }

    @PostMapping("/medicine")
    public String addMedicine(@ModelAttribute("addNewMedicine")AddMedicineControlModel model, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/doctor/medicine";
        }

        MedicineServiceModel serviceModel = modelMapper.map(model, MedicineServiceModel.class);
        medicineService.save(serviceModel);

        return "redirect:/doctor/medicine";
    }

    @GetMapping("/schedule")
    public ModelAndView getSchedule(ModelAndView modelAndView) {
        List<ScheduleViewModel> model =  scheduleService.getAll()
                .stream()
                .map(s -> modelMapper.map(s, ScheduleViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("schedules", model);
        modelAndView.setViewName("doctor/schedule");
        return modelAndView;
    }

    @ModelAttribute("addSchedule")
    public AddScheduleControllerModel addSchedule(){
        return new AddScheduleControllerModel();
    }

    @PostMapping("/schedule")
    public String addSchedule(@ModelAttribute("addSchedule")AddScheduleControllerModel model, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/doctor/schedule";
        }
        ScheduleServiceModel scheduleServiceModel = modelMapper.map(model, ScheduleServiceModel.class);
        scheduleService.save(scheduleServiceModel);

        return "redirect:/doctor/schedule";
    }

    private CurrentUser getCurrentUser(HttpSession session) {
        return (CurrentUser) session.getAttribute("username");
    }
}
