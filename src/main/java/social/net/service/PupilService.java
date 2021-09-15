package social.net.service;

import org.springframework.stereotype.Service;
<<<<<<< HEAD

@Service
public class PupilService {
    public void registerPupil() {

    }
=======
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

>>>>>>> 2deea0abf6dbd4f2548054cd90baf2a369a705f8
}
