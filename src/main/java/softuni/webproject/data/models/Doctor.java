package softuni.webproject.data.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

import static softuni.webproject.config.Constant.INVALID_TEXT_LENGTH_MASSAGE;
import static softuni.webproject.config.Constant.PASSWORD_VALIDATE;

@Entity
@Table(name = "doctors")
@Getter
@Setter
public class Doctor extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    @NotEmpty
    private String username;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = PASSWORD_VALIDATE)
    @NotEmpty
    private String password;

    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @Size(min = 3, max = 6)
    @NotEmpty
    private String specialization;

    @Column(nullable = false)
    @Size(min = 3, max = 20)
    @NotEmpty
    private String description;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Animal> animals;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "logInKey_id")
    private IdentificationKey logInKey;

    @Column(name = "image_url")
    private String imageUrl;

//    public Doctor() {
//        animals = new ArrayList<>();
//        schedules = new ArrayList<>();
//    }
}
