package softuni.webproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnimalServiceModel {
    private String breed;
    private String name;
    private Integer age;
    private String doctor;
    private String disease;
    private String medicine;
    private String user;
}