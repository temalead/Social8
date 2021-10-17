package social.net.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import social.net.model.Pupil;
import social.net.model.StudyClass;
import social.net.service.PupilService;

import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
public class AddingInfoAboutUserController {
    private PupilService pupilService;

    @Autowired
    public AddingInfoAboutUserController(PupilService pupilService) {
        this.pupilService = pupilService;
    }

    @GetMapping
    public Pupil getProfileInfo(
            @AuthenticationPrincipal Pupil pupil
    ){
        return pupil;
    }
    @PostMapping
    public Pupil updateDataProfile(
            @AuthenticationPrincipal Pupil old,
            @Valid Pupil pupil

    ) throws IllegalAccessException {
        if (pupilService.update(old,pupil)){
            return pupil;
        }
        else{
            throw  new IllegalAccessException();
        }
    }
}
