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

    @OneToMany(mappedBy = "pupils",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "study_class_id")
    private StudyClass studyClass;

    private Role role;
}
