package softuni.webproject.data.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Animal extends BaseEntity{
    @Column(nullable = false)
    @NotNull
    private String breed;
    @Column(nullable = false)
    @NotNull
    private String name;
    @Column(nullable = false)
    @NotNull
    private Integer age;
    private String disease;
//    TODO
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToMany
    @JoinTable(name = "animal_medicine", joinColumns = {@JoinColumn(name = "animal_id")},
            inverseJoinColumns = {@JoinColumn(name = "medicine_id")})
    private List<Medicine> medicines;
}
