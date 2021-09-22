package social.net.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import social.net.model.Pupil;

import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
public class AddingInfoAboutUserController {
    @GetMapping
    public Pupil getProfileInfo(
            @AuthenticationPrincipal Pupil pupil
    ){
        return pupil;
    }
    @PostMapping
    public Pupil updateDataProfile(
            @Valid Pupil pupil
    ){
        return null;
    }
}
