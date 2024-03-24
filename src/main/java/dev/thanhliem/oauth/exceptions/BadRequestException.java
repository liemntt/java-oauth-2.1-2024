package dev.thanhliem.oauth.exceptions;

import dev.thanhliem.oauth.constants.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends ApplicationException {

    public BadRequestException(ErrorCodes code, String message) {
        super(code, message);
    }

    public BadRequestException(ErrorCodes code, String message, String detail) {
        super(code, message, detail);
    }
}