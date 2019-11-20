package softuni.webproject.data.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    private String breed;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String name;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private Integer age;
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
