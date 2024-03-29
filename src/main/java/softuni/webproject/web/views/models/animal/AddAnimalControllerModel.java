package softuni.webproject.web.views.models.animal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class AddAnimalControllerModel {
    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3, max = 10)
    private String breed;
    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3, max = 10)
    private String name;
    @Column(nullable = false)
    @NotNull
    @Min(value = 0)
    private double age;
    private String doctor;
    @Size(min = 3, max = 10)
    private String disease;
    private String medicine;
    private String user;
}
