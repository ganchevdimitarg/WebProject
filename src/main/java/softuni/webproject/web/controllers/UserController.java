package softuni.webproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/user-home")
    public String getUserHome() {
        return "/user/user-home.html";
    }

    @GetMapping("/add-pet")
    public String getAddPet() {
        return "/user/add-pet.html";
    }

    @GetMapping("/pet")
    public String getPet() {
        return "/user/pet.html";
    }

    @GetMapping("/contact")
    public String getContact(){
        return "/user/contact.html";
    }

}
