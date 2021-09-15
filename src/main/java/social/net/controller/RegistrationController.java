package social.net.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import social.net.model.Pupil;
import social.net.service.PupilService;

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
    public boolean registrationPost(){
        pupilService.registerPupil();
        return true;
    }
}