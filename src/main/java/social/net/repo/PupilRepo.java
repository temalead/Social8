package social.net.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import social.net.model.Pupil;

public interface PupilRepo extends JpaRepository<Pupil,Long> {

    Pupil findByEmail(String login);

    Pupil findByActivationCode(String code);
    Pupil findByVkId(Integer id);

}
