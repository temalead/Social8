package social.net.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import social.net.model.Pupil;
import social.net.model.Role;
import social.net.repo.PupilRepo;

import java.util.UUID;

@Service
public class PupilService implements UserDetailsService {
    private final PupilRepo pupilRepo;
    private final Pupil pupil;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Autowired
    public PupilService(PupilRepo pupilRepo, Pupil pupil, PasswordEncoder passwordEncoder, MailService mailService) {
        this.pupilRepo = pupilRepo;
        this.pupil = pupil;
        this.passwordEncoder=passwordEncoder;
        this.mailService = mailService;
    }


    public boolean registerPupil(Pupil pupil) throws Exception {
        Pupil pupilFromDb = pupilRepo.findByEmail(pupil.getEmail());
        if (pupilFromDb!=null){
            throw new Exception("Something was happened word");
        }
        pupil.setActive(false);
        pupil.setActivationCode(UUID.randomUUID().toString());
        pupil.setPassword(passwordEncoder.encode(pupil.getPassword()));
        pupil.setRole(Role.PUPIL);
        pupilRepo.save(pupil);
        mailService.sendEmail(pupil.getEmail(),"Activation Code",
                mailService.makeMessage(pupil.getActivationCode()));
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Pupil byLogin = pupilRepo.findByEmail(s);
        if (byLogin ==null){
            try {
                throw new Exception("User not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return byLogin;
    }

    public boolean activate(String code) {
        Pupil byActivationCode = pupilRepo.findByActivationCode(code);
        if (byActivationCode!=null){
            pupil.setActivationCode(null);
            pupil.setActive(true);
            pupilRepo.save(pupil);
            return true;
        }
        return false;
    }
}
