package softuni.webproject.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.webproject.errors.LogInHandleException;
import softuni.webproject.services.models.BaseServiceModel;
import softuni.webproject.services.models.DoctorRegisterServiceModel;
import softuni.webproject.services.models.LogInServiceModel;
import softuni.webproject.services.models.UserRegisterServiceModel;
import softuni.webproject.services.services.AuthService;
import softuni.webproject.services.services.LogInIdentificationKeyService;
import softuni.webproject.web.models.BaseControllerModel;
import softuni.webproject.web.models.DoctorRegisterControllerModel;
import softuni.webproject.web.models.UserRegisterControllerModel;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthController {
    private final AuthService auth;
    private final LogInIdentificationKeyService logInIdentificationKeyService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthService auth, LogInIdentificationKeyService logInIdentificationKeyService, ModelMapper modelMapper) {
        this.auth = auth;
        this.logInIdentificationKeyService = logInIdentificationKeyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/sign-in")
    public String getSignIn() {
        return "auth/sign-in.html";
    }

    @PostMapping("/sign-in")
    public String logIn(@ModelAttribute BaseControllerModel model, HttpSession session){
        BaseServiceModel user = modelMapper.map(model, BaseServiceModel.class);
        try {
            LogInServiceModel login = auth.logIn(user);
            session.setAttribute("name", login);
            if (model.getLogInKey().isEmpty()){
                return "redirect:user/user-home";
            }
            return "redirect:doctor/doctor-home";
        }catch (Exception e){
            return "redirect:/sign-in";
        }
    }

    //User
    @GetMapping("/sign-up-user")
    public String getSignUpPatient() {
        return "auth/sign-up-user.html";
    }

    @ModelAttribute("registerUser")
    public UserRegisterControllerModel registerUser(){
        return new UserRegisterControllerModel();
    }

    @PostMapping("/sign-up-user")
    public String registerPatient(@Valid @ModelAttribute("registerUser") UserRegisterControllerModel user, BindingResult bindingResult) throws IllegalAccessException {
        if (bindingResult.hasErrors()){
            return "auth/sign-up-user";
        }
        UserRegisterServiceModel userModel = modelMapper.map(user, UserRegisterServiceModel.class);
        auth.registerUser(userModel);

        return "redirect:/sign-in";
    }

    //Doctor
    @GetMapping("/sign-up-doctor")
    public String getSignUpDoctor() {
        logInIdentificationKeyService.generateKey();
        return "auth/sign-up-doctor.html";
    }

    @ModelAttribute("registerDoctor")
    public DoctorRegisterControllerModel registerDoctor(){
        return new DoctorRegisterControllerModel();
    }

    @PostMapping("/sign-up-doctor")
    public String registerDoctor(@Valid @ModelAttribute("registerDoctor") DoctorRegisterControllerModel model, BindingResult bindingResult) throws IllegalAccessException {
        if (bindingResult.hasErrors()){
            return "auth/sign-up-doctor";
        }
        DoctorRegisterServiceModel doctorModel = modelMapper.map(model, DoctorRegisterServiceModel.class);
        auth.registerDoctor(doctorModel);

        return "redirect:/sign-in";
    }


    @ExceptionHandler(LogInHandleException.class)
    public ModelAndView handleException(LogInHandleException ex){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());

        return modelAndView;
    }
}