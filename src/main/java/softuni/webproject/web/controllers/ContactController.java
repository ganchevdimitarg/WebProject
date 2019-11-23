package softuni.webproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @GetMapping("/contact/contact")
    public String getContact(){
        return "contact/contact";
    }
}
