package softuni.webproject.web.views.models.medicine;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AddMedicineControlModel {
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String name;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String description;

    private MultipartFile image;
}
