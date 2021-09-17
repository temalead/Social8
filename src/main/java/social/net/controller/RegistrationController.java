package social.net.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import social.net.model.Pupil;
import social.net.service.PupilService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("registration")
public class RegistrationController {
    private final PupilService pupilService;

    public RegistrationController(PupilService pupilService) {
        this.pupilService = pupilService;
    }
    @GetMapping
    public ResponseEntity getRegistrationPage(){
        return ResponseEntity.ok().body("Ok!!!");
    }
    @PostMapping
    public Pupil registrationPost(
            @RequestBody Pupil pupil,
            HttpServletResponse response
            ) throws IOException {
        if (!pupilService.registerPupil(pupil)){
            response.sendRedirect("/registration");
        }
        return pupil;
    }

}