package social.net.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.net.model.Pupil;
import social.net.service.PupilService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("registration")
public class RegistrationController {
    private final PupilService pupilService;

    public RegistrationController(PupilService pupilService) {
        this.pupilService = pupilService;
    }
    @PostMapping
    public Pupil registrationPost(
            @RequestBody Pupil pupil,
            HttpServletResponse response
            ) throws Exception {
        if (!pupilService.registerPupil(pupil)){
            response.sendRedirect("/registration");
            return pupil;
        }
        response.sendRedirect("/");
        return pupil;
    }

}