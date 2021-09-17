package social.net.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Entity
@Table(name = "study_class")
public class StudyClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private Integer numberOfClass;



    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Pupil> pupils;

    public StudyClass() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfClass() {
        return numberOfClass;
    }

    public void setNumberOfClass(Integer numberOfClass) {
        this.numberOfClass = numberOfClass;
    }

    public Set<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(Set<Pupil> pupils) {
        this.pupils = pupils;
    }
}

