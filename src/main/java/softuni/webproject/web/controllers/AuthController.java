package softuni.webproject.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.webproject.services.models.BaseServiceModel;
import softuni.webproject.services.models.DoctorRegisterServiceModel;
import softuni.webproject.services.models.LogInServiceModel;
import softuni.webproject.services.models.UserRegisterServiceModel;
import softuni.webproject.services.services.AuthService;
import softuni.webproject.web.models.BaseControllerModel;
import softuni.webproject.web.models.DoctorRegisterControllerModel;
import softuni.webproject.web.models.UserRegisterControllerModel;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthController {
    private final AuthService auth;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthService auth, ModelMapper modelMapper) {
        this.auth = auth;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/sign-in")
    public String getSignIn() {
        return "auth/sign-in.html";
    }

    @PostMapping("/sign-in")
    public String logIn(@ModelAttribute BaseControllerModel model, HttpSession session){
        BaseServiceModel user = this.modelMapper.map(model, BaseServiceModel.class);
        try {
            LogInServiceModel login = auth.logIn(user);
            session.setAttribute("user", login);
            return "redirect:/home";
//            return "/home-user";
        }catch (Exception e){
            return "redirect:/sign-in";
        }
    }

    //User
    @ModelAttribute("registerUser")
    public UserRegisterControllerModel registerUser(){
        return new UserRegisterControllerModel();
    }

    @GetMapping("/sign-up-user")
    public String getSignUpPatient() {
        return "auth/sign-up-user.html";
    }

    @PostMapping("/sign-up-user")
    public String registerPatient(@Valid @ModelAttribute("registerUser") UserRegisterControllerModel user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "auth/sign-up-user";
        }
        UserRegisterServiceModel userModel = this.modelMapper.map(user, UserRegisterServiceModel.class);
        auth.registerUser(userModel);

        return "redirect:/sign-in";
    }

    //Doctor
    @ModelAttribute("registerDoctor")
    public DoctorRegisterControllerModel registerDoctor(){
        return new DoctorRegisterControllerModel();
    }

    @GetMapping("/sign-up-doctor")
    public String getSignUpDoctor() {
        return "auth/sign-up-doctor.html";
    }

    @PostMapping("/sign-up-doctor")
    public String registerDoctor(@Valid @ModelAttribute("registerDoctor") DoctorRegisterControllerModel model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "auth/sign-up-doctor";
        }
        DoctorRegisterServiceModel doctorModel = this.modelMapper.map(model, DoctorRegisterServiceModel.class);
        auth.registerDoctor(doctorModel);

        return "redirect:/sign-in";
    }

}
