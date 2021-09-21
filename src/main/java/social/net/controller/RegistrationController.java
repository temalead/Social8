package social.net.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import social.net.model.Pupil;
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
    public Pupil registrationPost(
            Model model,
            @RequestBody Pupil pupil,
            HttpServletResponse response
            ) throws Exception {
        if (!pupilService.registerPupil(pupil)){
            model.addAttribute("Something was wrong");
            response.sendRedirect("/registration");
            return pupil;
        }
        response.sendRedirect("/");
        return pupil;
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