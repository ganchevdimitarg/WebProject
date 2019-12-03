package softuni.webproject.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.webproject.services.models.AnimalServiceModel;
import softuni.webproject.services.models.CurrentUser;
import softuni.webproject.services.models.UserServiceModel;
import softuni.webproject.services.services.animal.AnimalService;
import softuni.webproject.services.services.user.UserService;
import softuni.webproject.web.models.animal.AddAnimalControllerModel;
import softuni.webproject.web.models.animal.AnimalViewModel;
import softuni.webproject.web.models.user.UserUpdateControllerModel;
import softuni.webproject.web.models.user.UserViewModel;

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
        CurrentUser currentUser = getCurrentUser(session);
        UserViewModel userView = modelMapper.map(userService.findByUsername(currentUser.getUsername()), UserViewModel.class);
        modelAndView.addObject("user", userView);
        modelAndView.setViewName("user/user-home.html");
        return modelAndView;
    }

    @ModelAttribute("updateUserInfo")
    public UserUpdateControllerModel updateUserInfo(){
        return new UserUpdateControllerModel();
    }

    @PostMapping("/user-home")
    public void update(@Valid @ModelAttribute("updateUserInfo") UserUpdateControllerModel user, HttpSession session){
        UserServiceModel model = modelMapper.map(user, UserServiceModel.class);
        userService.update(model);
    }

    @PostMapping("/user-home/delete")
    public String delete(HttpSession session) {
        CurrentUser currentUser = getCurrentUser(session);
        userService.deleteUser(currentUser.getUsername());
        session.invalidate();

        return "redirect:/";
    }

//    TODO
//    @PostMapping("/user-home/delete-pet")
//    public String deletePet(@ModelAttribute("animal") String id, HttpSession session) {
//        LogInServiceModel logInServiceModel = getCurrentUser(session);
//        userService.deletePet(logInServiceModel.getUsername(), id);
//
//        return "redirect:/user/pet";
//    }

    @GetMapping("/add-pet")
    public String getAddPet() {
        return "/user/add-pet.html";
    }

    @ModelAttribute("addPetModel")
    public AddAnimalControllerModel addPetModel(){
        return new AddAnimalControllerModel();
    }

    @PostMapping("/add-pet")
    public String addPet(@Valid @ModelAttribute("addPetModel") AddAnimalControllerModel animal, BindingResult bindingResult, HttpSession session){
        if (bindingResult.hasErrors()){
            return "/user/add-pet";
        }
        AnimalServiceModel animalServiceModel = modelMapper.map(animal, AnimalServiceModel.class);
        CurrentUser currentUser = getCurrentUser(session);

        animalService.save(animalServiceModel, currentUser.getUsername());
        session.setAttribute("animalName", animalServiceModel.getName());
        return "redirect:/user/pet";
    }

    @GetMapping("/pet")
    public ModelAndView getUserAnimals(ModelAndView modelAndView, HttpSession session){
        CurrentUser currentUser = getCurrentUser(session);
        List<AnimalViewModel> animals = animalService.getCurrentUserAnimal(currentUser.getUsername())
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

    private CurrentUser getCurrentUser(HttpSession session) {
        return (CurrentUser) session.getAttribute("username");
    }
}
