package softuni.webproject.web.views.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getIndex(HttpSession session) {
        return "/index.html";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "/about.html";
    }
}
