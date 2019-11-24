package softuni.webproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @GetMapping("/doctor-home")
    public String getDoctorHome() {
        return "/doctor/doctor-home.html";
    }

    @GetMapping("/medicine")
    public String getMedicine() {
        return "/doctor/medicine.html";
    }

    @GetMapping("/schedule")
    public String getSchedule() {
        return "/doctor/schedule.html";
    }
}
