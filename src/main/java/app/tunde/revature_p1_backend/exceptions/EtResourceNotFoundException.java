package app.tunde.revature_p1_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EtResourceNotFoundException extends RuntimeException {
    public EtResourceNotFoundException(String message) {
        super(message);
    }
}
