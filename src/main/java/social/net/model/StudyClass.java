package social.net.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Component
@NoArgsConstructor
@Entity
@Setter
@Getter
public class StudyClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private Integer numberOfClass;

    private Set<Pupil> pupilsInStudyClass;
}
