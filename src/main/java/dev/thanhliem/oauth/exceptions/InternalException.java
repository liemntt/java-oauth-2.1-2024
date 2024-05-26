package dev.thanhliem.oauth.exceptions;

import dev.thanhliem.oauth.constants.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalException extends ApplicationException {

    public InternalException(ErrorCodes code, String message) {
        super(code, message);
    }

    public InternalException(ErrorCodes code, String message, String detail) {
        super(code, message, detail);
    }
}