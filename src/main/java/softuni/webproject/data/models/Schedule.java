package softuni.webproject.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule extends BaseEntity {
    @Column(name = "data_of_review", nullable = false, unique = true)
    @NotEmpty
    @Pattern(regexp = "[0-9.: ]+", message = "Incorrect date")
    private String dateReview;
    @ManyToOne
    @JoinColumn(name = "doctors_id")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "animals_id")
    private Animal animal;
}
