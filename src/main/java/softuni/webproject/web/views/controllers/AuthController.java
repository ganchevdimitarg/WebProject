package softuni.webproject.web.views.controllers;

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
import softuni.webproject.services.models.DoctorServiceModel;
import softuni.webproject.services.models.CurrentUser;
import softuni.webproject.services.models.UserServiceModel;
import softuni.webproject.services.services.auth.AuthService;
import softuni.webproject.services.services.doctor.IdentificationKeyService;
import softuni.webproject.web.views.models.BaseControllerModel;
import softuni.webproject.web.views.models.doctor.DoctorControllerModel;
import softuni.webproject.web.views.models.user.UserControllerModel;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthController {
    private final AuthService auth;
    private final IdentificationKeyService identificationKeyService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthService auth, IdentificationKeyService identificationKeyService, ModelMapper modelMapper) {
        this.auth = auth;
        this.identificationKeyService = identificationKeyService;
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
            CurrentUser login = auth.logIn(user);
            session.setAttribute("username", login);
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
    public UserControllerModel registerUser(){
        return new UserControllerModel();
    }

    @PostMapping("/sign-up-user")
    public String registerPatient(@Valid @ModelAttribute("registerUser") UserControllerModel user, BindingResult bindingResult) throws IllegalAccessException {
        if (bindingResult.hasErrors()){
            return "auth/sign-up-user";
        }
        UserServiceModel userModel = modelMapper.map(user, UserServiceModel.class);
        auth.registerUser(userModel);

        return "redirect:/sign-in";
    }

    //Doctor
    @GetMapping("/sign-up-doctor")
    public String getSignUpDoctor() {
        identificationKeyService.generateKey();
        return "auth/sign-up-doctor.html";
    }

    @ModelAttribute("registerDoctor")
    public DoctorControllerModel registerDoctor(){
        return new DoctorControllerModel();
    }

    @PostMapping("/sign-up-doctor")
    public String registerDoctor(@Valid @ModelAttribute("registerDoctor") DoctorControllerModel model, BindingResult bindingResult) throws IllegalAccessException {
        if (bindingResult.hasErrors()){
            return "auth/sign-up-doctor";
        }
        DoctorServiceModel doctorModel = modelMapper.map(model, DoctorServiceModel.class);
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