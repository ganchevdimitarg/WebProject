package softuni.webproject.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "No such animal")
public class AnimalErrorHandlerException extends RuntimeException {

    public AnimalErrorHandlerException(String message) {
        super(message);
    }
}
