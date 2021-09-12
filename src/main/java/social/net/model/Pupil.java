package social.net.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "pupil")
public class Pupil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String LastName;

    private String password;

    @ManyToOne
    @JoinColumn(name = "study_class")
    private StudyClass studyClass;

    private Role role;
}
