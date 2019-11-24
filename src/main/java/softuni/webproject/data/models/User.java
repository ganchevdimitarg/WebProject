package softuni.webproject.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

import static softuni.webproject.config.Constant.*;

@Entity
@Table(name = "owners")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    @NotEmpty
    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    private String username;
    @Column(nullable = false, unique = true)
    @NotEmpty
    @Pattern(regexp = PASSWORD_VALIDATE)
    private String password;
    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3, max = 20, message = INVALID_TEXT_LENGTH_MASSAGE)
    private String name;
    @Column(nullable = false, unique = true)
    @NotEmpty
    @Pattern(regexp = EMAIL_VALIDATE)
    private String email;
    @Column(nullable = false)
    @NotEmpty
    private String address;
    @Column(name = "phone_number",nullable = false)
    @NotEmpty
    @Pattern(regexp = PHONE_NUMBER_VALIDATE)
    private String phoneNumber;
    @OneToMany(mappedBy = "user")
    private List<Animal> animals;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Schedule schedule;
}
