package softuni.webproject.web.views.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.webproject.services.models.CurrentUser;
import softuni.webproject.services.models.MedicineServiceModel;
import softuni.webproject.services.models.ScheduleServiceModel;
import softuni.webproject.services.models.TreatmentServiceModel;
import softuni.webproject.services.services.animal.AnimalService;
import softuni.webproject.services.services.cloudinary.CloudinaryService;
import softuni.webproject.services.services.doctor.DoctorService;
import softuni.webproject.services.services.medicine.MedicineService;
import softuni.webproject.services.services.schedule.ScheduleService;
import softuni.webproject.web.views.models.doctor.DoctorViewModel;
import softuni.webproject.web.views.models.medicine.AddMedicineControlModel;
import softuni.webproject.web.views.models.schedule.AddScheduleControllerModel;
import softuni.webproject.web.views.models.schedule.AddTreatmentControllerModel;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/doctor")
@AllArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final AnimalService animalService;
    private final MedicineService medicineService;
    private final ScheduleService scheduleService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    @GetMapping("/doctor-home")
    public ModelAndView getDoctorHome(ModelAndView modelAndView, HttpSession session) {
        CurrentUser currentUser = getCurrentUser(session);
        DoctorViewModel doctorViewModel = modelMapper.map(doctorService.findByUsername(currentUser.getUsername()), DoctorViewModel.class);
        modelAndView.addObject("doctor", doctorViewModel);
        modelAndView.setViewName("doctor/doctor-home");
        return modelAndView;
    }

    //  -------------------- Medicine --------------------
    @GetMapping("/medicine")
    public String getMedicine() {
        return "doctor/medicine.html";
    }

    @ModelAttribute("addNewMedicine")
    public AddMedicineControlModel addNewMedicine() {
        return new AddMedicineControlModel();
    }

    @PostMapping("/medicine")
    public String addMedicine(@ModelAttribute("addNewMedicine") AddMedicineControlModel model, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "/doctor/medicine";
        }

        MedicineServiceModel serviceModel = modelMapper.map(model, MedicineServiceModel.class);
        serviceModel.setImageUrl(cloudinaryService.upload(model.getImage()));
        medicineService.save(serviceModel);

        return "redirect:/doctor/medicine";
    }
//  -------------------- End Medicine --------------------

    //  -------------------- Schedule --------------------
    @GetMapping("/schedule")
    public String getSchedule() {
        return "doctor/schedule.html";
    }

    @ModelAttribute("addSchedule")
    public AddScheduleControllerModel addSchedule() {
        return new AddScheduleControllerModel();
    }

    @PostMapping("/schedule")
    public String addSchedule(@ModelAttribute("addSchedule") AddScheduleControllerModel model, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "/doctor/schedule";
        }
        session.setAttribute("animalName", model.getAnimal());
        ScheduleServiceModel scheduleServiceModel = modelMapper.map(model, ScheduleServiceModel.class);
        scheduleService.save(scheduleServiceModel);

        return "redirect:/doctor/schedule";
    }
//  -------------------- End Schedule --------------------

    //  -------------------- Treatment --------------------
    @GetMapping("/add-treatment/{animalName}")
    public String getSchedule(@PathVariable("animalName") String animalName, HttpSession session) {
        session.setAttribute("animalName", animalName);
        return "/doctor/add-treatment.html";
    }

    @ModelAttribute("treatmentAdd")
    public AddTreatmentControllerModel treatmentAdd() {
        return new AddTreatmentControllerModel();
    }

    @PostMapping("/add-treatment")
    public String addTreatment(@Valid @ModelAttribute AddTreatmentControllerModel addTreatment, BindingResult bindingResult, HttpSession session) throws IllegalAccessException {
        if (bindingResult.hasErrors()) {
            return "/doctor/add-treatment";
        }
        String animalName = session.getAttribute("animalName").toString();
        TreatmentServiceModel treatment = modelMapper.map(addTreatment, TreatmentServiceModel.class);
        animalService.addMedicineDisease(animalName, treatment.getMedicine(), treatment.getDisease());
        return "redirect:/doctor/schedule";
    }
//  -------------------- End Treatment --------------------

    @PostMapping("/schedule/delete/{date}")
    public String finished(@PathVariable("date") String date) {
        scheduleService.deleteScheduleByDateReview(date);

        return "redirect:/doctor/schedule";
    }

    private CurrentUser getCurrentUser(HttpSession session) {
        return (CurrentUser) session.getAttribute("username");
    }
}
