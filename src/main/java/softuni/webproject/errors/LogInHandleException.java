package softuni.webproject.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.IM_USED, reason = "Not found")
public class LogInHandleException extends RuntimeException {
    public LogInHandleException(String message) {
        super(message);
    }
}