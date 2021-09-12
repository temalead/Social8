package social.net.service;

import org.springframework.stereotype.Service;
import social.net.model.Pupil;
import social.net.repo.PupilRepo;

@Service
public class PupilService {
    private final PupilRepo pupilRepo;
    private final Pupil pupil;

    public PupilService(PupilRepo pupilRepo, Pupil pupil) {
        this.pupilRepo = pupilRepo;
        this.pupil = pupil;
    }

}
