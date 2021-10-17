package social.net.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import social.net.model.Pupil;
import social.net.model.View;
import social.net.service.PupilService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("registration")
public class RegistrationController {
    private final PupilService pupilService;
    @Value("${activation.address}")
    private String activationAddress;

    public RegistrationController(PupilService pupilService) {
        this.pupilService = pupilService;
    }
    @PostMapping
    @JsonView(View.MessageRegistration.class)
    public ResponseEntity<Pupil> registrationPost(
            @RequestBody Pupil pupil,
            HttpServletResponse response
            ) throws Exception {
        if (!pupilService.registerPupil(pupil)){
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
        }
        return new ResponseEntity(pupil, HttpStatus.OK);
    }

    @GetMapping("/activate/{code}")
    public void activate(Model model,
                         @PathVariable("code") String code,
                         HttpServletResponse response,
                         @AuthenticationPrincipal Pupil pupil
                         ) throws IOException {
        if (!pupilService.activate(code)) {
            model.addAttribute("You are already activate account");
            response.sendRedirect(activationAddress+pupil.getActivationCode());
        }
        model.addAttribute("Successful activation");
        response.sendRedirect("/");
    }

}