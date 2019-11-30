package softuni.webproject.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.webproject.services.models.AnimalServiceModel;
import softuni.webproject.services.models.LogInServiceModel;
import softuni.webproject.services.services.AnimalService;
import softuni.webproject.services.services.UserService;
import softuni.webproject.web.models.AnimalAddPetControllerModel;
import softuni.webproject.web.models.AnimalViewModel;
import softuni.webproject.web.models.UserViewModel;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AnimalService animalService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, AnimalService animalService, ModelMapper modelMapper) {
        this.userService = userService;
        this.animalService = animalService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/user-home")
    public ModelAndView getUserHome(ModelAndView modelAndView, HttpSession session) {
        LogInServiceModel logInServiceModel = getCurrentUser(session);
        UserViewModel userView = modelMapper.map(userService.findByName(logInServiceModel.getName()), UserViewModel.class);
        modelAndView.addObject("user", userView);
        modelAndView.setViewName("user/user-home.html");
        return modelAndView;
    }

    @DeleteMapping("/user-home/delete")
    public String deleteBuyer(HttpSession session) {
        LogInServiceModel logInServiceModel = getCurrentUser(session);
        userService.deleteUser(logInServiceModel.getName());

        return "redirect:/user/user-home";
    }

    @GetMapping("/add-pet")
    public String getAddPet() {
        return "/user/add-pet.html";
    }

    @ModelAttribute("addPetModel")
    public AnimalAddPetControllerModel addPetModel(){
        return new AnimalAddPetControllerModel();
    }

    @PostMapping("/add-pet")
    public String addPet(@Valid @ModelAttribute("addPetModel") AnimalAddPetControllerModel animal, BindingResult bindingResult, HttpSession session){
        if (bindingResult.hasErrors()){
            return "/user/add-pet";
        }
        AnimalServiceModel animalServiceModel = modelMapper.map(animal, AnimalServiceModel.class);
        LogInServiceModel logInServiceModel = getCurrentUser(session);

        animalService.save(animalServiceModel, logInServiceModel.getName());
        session.setAttribute("animalName", animalServiceModel.getName());
        return "redirect:/user/pet";
    }

    @GetMapping("/pet")
    public ModelAndView getUserAnimals(ModelAndView modelAndView, HttpSession session){
        LogInServiceModel logInServiceModel = getCurrentUser(session);
        List<AnimalViewModel> animals = animalService.getCurrentUserAnimal(logInServiceModel.getName())
                .stream()
                .map(s -> modelMapper.map(s, AnimalViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("animals", animals);
        modelAndView.setViewName("user/pet.html");
        return modelAndView;
    }

    @GetMapping("/contact")
    public String getContact(){
        return "/user/contact";
    }

    private LogInServiceModel getCurrentUser(HttpSession session) {
        return (LogInServiceModel) session.getAttribute("name");
    }
}
