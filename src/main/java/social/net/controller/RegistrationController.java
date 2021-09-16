package social.net.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import social.net.model.Pupil;
import social.net.service.PupilService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController("/registration")
public class RegistrationController {
    private final PupilService pupilService;

    public RegistrationController(PupilService pupilService) {
        this.pupilService = pupilService;
    }

    @GetMapping
    public String toRegisterForm(){
        return "registration";
        
    }
    @PostMapping
    public Pupil registrationPost(
            @Valid Pupil pupil,
            BindingResult bindingResult,
            Model model,
            HttpServletResponse response
            ) throws IOException {
        if (!pupilService.registerPupil(pupil)){
            response.sendRedirect("/registration");
        }
        return pupil;
    }
}