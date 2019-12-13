package softuni.webproject.web.views.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.webproject.errors.AnimalErrorHandlerException;
import softuni.webproject.errors.UserNotFoundException;
import softuni.webproject.services.models.AnimalServiceModel;
import softuni.webproject.services.models.CurrentUser;
import softuni.webproject.services.models.UserServiceModel;
import softuni.webproject.services.services.animal.AnimalService;
import softuni.webproject.services.services.user.UserService;
import softuni.webproject.web.views.models.animal.AddAnimalControllerModel;
import softuni.webproject.web.views.models.animal.AnimalViewModel;
import softuni.webproject.web.views.models.user.UserUpdateControllerModel;
import softuni.webproject.web.views.models.user.UserViewModel;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final AnimalService animalService;
    private final ModelMapper modelMapper;

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
    public ModelAndView update(@Valid @ModelAttribute("updateUserInfo") UserUpdateControllerModel user, ModelAndView modelAndView, HttpSession session){
        UserServiceModel model = modelMapper.map(user, UserServiceModel.class);
        userService.update(model);
          return this.getUserHome(modelAndView, session);
    }

    @PostMapping("/user-home/delete")
    public String delete(HttpSession session) {
        CurrentUser currentUser = getCurrentUser(session);
        userService.deleteUser(currentUser.getUsername());
        session.invalidate();

        return "redirect:/";
    }


//  Animal
    @PostMapping("/pet/delete/{name}")
    public String deletePet(@PathVariable("name") String name, HttpSession session) {
        CurrentUser currentUser = getCurrentUser(session);
        userService.deletePet(name, currentUser);

        return "redirect:/user/pet";
    }

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
        List<AnimalServiceModel> animal = animalService.getCurrentUserAnimal(currentUser.getUsername());
        List<AnimalViewModel> animals = animalService.getCurrentUserAnimal(currentUser.getUsername())
                .stream()
                .map(s -> modelMapper.map(s, AnimalViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("animals", animals);
        modelAndView.setViewName("user/pet");
        return modelAndView;
    }

    @GetMapping("/contact")
    public String getContact(){
        return "/user/contact";
    }

    private CurrentUser getCurrentUser(HttpSession session) {
        return (CurrentUser) session.getAttribute("username");
    }

    @ExceptionHandler({AnimalErrorHandlerException.class, UserNotFoundException.class})
    public ModelAndView handleException(Throwable exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }
}
