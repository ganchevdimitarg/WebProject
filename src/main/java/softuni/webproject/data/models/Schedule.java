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
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String data;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private boolean isFinished;
}
