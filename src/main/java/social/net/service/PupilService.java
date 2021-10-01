package social.net.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import social.net.model.Pupil;
import social.net.model.Role;
import social.net.repo.PupilRepo;
import sun.misc.Cache;

import java.util.Optional;
import java.util.UUID;

@Service
public class PupilService implements UserDetailsService {
    private final PupilRepo pupilRepo;
    private final Pupil pupil;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private static final Logger logger= LoggerFactory.getLogger(PupilService.class);

    @Autowired
    public PupilService(PupilRepo pupilRepo, Pupil pupil, PasswordEncoder passwordEncoder, MailService mailService) {
        this.pupilRepo = pupilRepo;
        this.pupil = pupil;
        this.passwordEncoder=passwordEncoder;
        this.mailService = mailService;
    }


    public boolean registerPupil(Pupil pupil) throws Exception {
        Optional<Pupil> pupilFromDb = Optional.ofNullable(pupilRepo.findByEmail(pupil.getEmail()));
        if (pupilFromDb.isPresent()){
            logger.error(String.valueOf(new RuntimeException()),"User was already created");
            throw new Exception(String.format("User with %s was already created",pupil.getEmail()));
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
        Optional<Pupil> pupilByEmailOpt = Optional.ofNullable(pupilRepo.findByEmail(s));
        if (pupilByEmailOpt.isPresent()){
            return pupilByEmailOpt.get();
        }
        logger.error(String.valueOf(new RuntimeException()),"User not found");
        throw new RuntimeException("User not found");

    }

    public boolean activate(String code) {
        Optional<Pupil> byActivationCode = Optional.ofNullable(pupilRepo.findByActivationCode(code));
        if (byActivationCode.isPresent()){
            pupil.setActivationCode(null);
            pupil.setActive(true);
            pupilRepo.save(pupil);
            return true;
        }
        return false;
    }
}
