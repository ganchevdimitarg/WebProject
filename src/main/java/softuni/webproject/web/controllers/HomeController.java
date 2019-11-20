package softuni.webproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getIndex(HttpSession session) {
        return "/index.html";
    }

    @GetMapping("/home")
    public String home(){
        return "/home.html";
    }
}
