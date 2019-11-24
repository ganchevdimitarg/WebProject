package softuni.webproject.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Something wrong happened.")
public class LogInHandleException extends RuntimeException {
    public LogInHandleException(String message, Throwable cause) {
        super(message, cause);
    }
}
