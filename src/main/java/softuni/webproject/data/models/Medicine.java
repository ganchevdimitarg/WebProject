package softuni.webproject.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "medicines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicine extends BaseEntity {
    @Column(nullable = false, unique = true)
    @NotNull
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String description;

    @ManyToMany(mappedBy = "medicines", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Animal> animals;

    @Column(name = "image_url")
    private String imageUrl;
}
