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
public class Pupil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String LastName;

    @OneToMany(mappedBy = "pupils",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "studyClass_id")
    private StudyClass studyClass;

    private Set<Pupil> subscribers;
    private Set<Pupil> subscriptions;


}
