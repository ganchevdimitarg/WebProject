package softuni.webproject.data.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "animals")
@Getter
@Setter
@NoArgsConstructor
public class Animal extends BaseEntity{
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 6)
    private String breed;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 6)
    private String name;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 2)
    private Integer age;
    @Size(min = 3, max = 10)
    private String disease;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToMany
    @JoinTable(name = "animal_medicine", joinColumns = {@JoinColumn(name = "animal_id")},
            inverseJoinColumns = {@JoinColumn(name = "medicine_id")})
    private List<Medicine> medicines;
}
