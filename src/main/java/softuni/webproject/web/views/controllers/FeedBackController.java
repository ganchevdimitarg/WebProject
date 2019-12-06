package softuni.webproject.web.views.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import softuni.webproject.config.EmailConfiguration;
import softuni.webproject.services.models.FeedbackServiceModel;
import softuni.webproject.services.services.feedback.FeedbackService;
import softuni.webproject.web.views.models.feedback.FeedbackControllerModel;

import javax.validation.ValidationException;

@RestController
@RequestMapping("/user")
public class FeedBackController {
    private final EmailConfiguration emailCfg;
    private final FeedbackService feedbackService;
    private final ModelMapper modelMapper;

    @Autowired
    public FeedBackController(EmailConfiguration emailCfg, FeedbackService feedbackService, ModelMapper modelMapper) {
        this.emailCfg = emailCfg;
        this.feedbackService = feedbackService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/contact")
    public ModelAndView sendFeedback(FeedbackControllerModel feedback, ModelAndView modelAndView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Feedback is not valid");
        }

        // Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        mailMessage.setTo("animalParadise@gmail.com");
        mailMessage.setSubject(feedback.getSubject());
        mailMessage.setText(feedback.getMessage());
        feedbackService.save(modelMapper.map(feedback, FeedbackServiceModel.class));
        // Send mail
        mailSender.send(mailMessage);

        modelAndView.setViewName("user/contact.html");

        return modelAndView;
    }
}
