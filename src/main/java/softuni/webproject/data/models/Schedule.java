package softuni.webproject.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
public class Schedule extends BaseEntity {
    @Column(name = "data_of_review", nullable = false)
    @NotNull
    @NotEmpty
    private String dateReview;
    @ManyToOne
    @JoinColumn(name = "doctors_id")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "animals_id")
    private Animal animal;
    private boolean isFinished;
}
