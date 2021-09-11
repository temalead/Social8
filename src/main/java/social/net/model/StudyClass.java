package social.net.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.EAGER)
    private Set<Pupil> pupils;
}

