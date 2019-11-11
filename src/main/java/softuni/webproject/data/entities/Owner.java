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
public class Owner extends BaseEntity {
    @Column(nullable = false, unique = true)
    @NotNull
    private String username;
    @Column(nullable = false, unique = true)
    @NotNull
    private String password;
    @Column(nullable = false, unique = true)
    @NotNull
    private String email;
    @Column(nullable = false)
    @NotNull
    private String name;
    @Column(nullable = false)
    @NotNull
    private String address;
    @Column(name = "phone_number",nullable = false)
    @NotNull
    private String phoneNumber;
//    TODO
    @OneToMany(mappedBy = "owner")
    private List<Animal> animals;

}
