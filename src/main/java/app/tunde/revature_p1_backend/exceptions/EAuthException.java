package app.tunde.revature_p1_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class EAuthException extends RuntimeException {

    public EAuthException(String message) {
        super(message);
    }
}