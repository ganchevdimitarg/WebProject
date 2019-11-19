package softuni.webproject.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
public class Owner extends BaseEntity {
    @Column(nullable = false, unique = true)
    @NotNull
    @NotEmpty
    private String username;
    @Column(nullable = false, unique = true)
    @NotNull
    @NotEmpty
    private String password;
    @Column(nullable = false, unique = true)
    @NotNull
    @NotEmpty
    private String email;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String name;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String address;
    @Column(name = "phone_number",nullable = false)
    @NotNull
    @NotEmpty
    private String phoneNumber;
    @OneToMany(mappedBy = "owner")
    private List<Animal> animals;
    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Schedule schedule;
}
