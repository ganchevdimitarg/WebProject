package softuni.webproject.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends BaseEntity {
    @Column(nullable = false, unique = true)
    @NotNull
    private String username;
    @Column(nullable = false, unique = true)
    @NotNull
    private String password;
    @Column(nullable = false)
    @NotNull
    private String name;
    @Column(nullable = false)
    @NotNull
    private String specialization;
    @Column(nullable = false)
    @NotNull
    private String description;
    @OneToMany(mappedBy = "doctor")
    private List<Animal> animals;
}
