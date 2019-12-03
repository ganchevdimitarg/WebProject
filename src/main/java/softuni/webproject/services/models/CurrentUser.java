package softuni.webproject.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CurrentUser {
    private String username;

    public CurrentUser(String username) {
        this.username = username;
    }
}
