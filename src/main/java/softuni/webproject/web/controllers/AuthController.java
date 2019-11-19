package softuni.webproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.webproject.web.models.SignUpDoctorModel;

@Controller
public class AuthController {

    @GetMapping("/sign-in")
    public ModelAndView getSignIn(ModelAndView modelAndView){
        return new ModelAndView("/auth/sign-in");
    }

    @GetMapping("/sign-up-patient")
    public ModelAndView getSignUpPatient(ModelAndView modelAndView){
        return new ModelAndView("/auth/sign-up-patient");
    }

    @GetMapping("/sign-up-doctor")
    public ModelAndView getSignUpDoctor(ModelAndView modelAndView){
        return new ModelAndView("/auth/sign-up-doctor");
    }


    @PostMapping("/sign-up-doctor")
    public ModelAndView createDoctor(@ModelAttribute SignUpDoctorModel model){
        return new ModelAndView("/auth/sign-in");
    }
}
