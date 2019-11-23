package softuni.webproject.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

import static softuni.webproject.config.Constant.INVALID_TEXT_LENGTH_MASSAGE;
import static softuni.webproject.config.Constant.PASSWORD_VALIDATE;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    private String username;
    @Column(nullable = false, unique = true)
    @Pattern(regexp = PASSWORD_VALIDATE)
    private String password;
    @Column(nullable = false)
    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    private String name;
    @Column(nullable = false)
    @Size(min = 3, max = 6)
    private String specialization;
    @Column(nullable = false)
    @Size(min = 3, max = 20)
    private String description;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Animal> animals;
    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Schedule schedule;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "logInKey_id")
    private LogInIdentificationKey logInKey;
}
