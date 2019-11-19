package softuni.webproject.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends BaseEntity {
    @Column(nullable = false, unique = true)
    @NotNull
    @NotEmpty
    private String username;
    @Column(nullable = false, unique = true)
    @NotNull
    @NotEmpty
    private String password;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String name;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String specialization;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String description;
    @OneToMany(mappedBy = "doctor")
    private List<Animal> animals;
    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Schedule schedule;
}
