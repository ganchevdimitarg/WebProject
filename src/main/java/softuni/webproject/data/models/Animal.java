package softuni.webproject.data.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "animals")
@Getter
@Setter
public class Animal extends BaseEntity{
    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3, max = 10)
    private String breed;
    @Column(nullable = false, unique = true)
    @NotEmpty
    @Size(min = 3, max = 10)
    private String name;
    @Column(nullable = false)
    @NotNull
    @Min(value = 0)
    private double age;
    @Size(min = 3, max = 10)
    private String disease;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToMany
    @JoinTable(
            name = "animals_medicines",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    private List<Medicine> medicines;
    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedules;
}
