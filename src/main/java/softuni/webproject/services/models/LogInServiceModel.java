package softuni.webproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LogInServiceModel {
    private String name;

    public LogInServiceModel(String name) {
        this.name = name;
    }
}
