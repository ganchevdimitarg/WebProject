package softuni.webproject.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found")
public class LogInHandleException extends IllegalAccessException {
    public LogInHandleException(String message) {
        super(message);
    }
}
