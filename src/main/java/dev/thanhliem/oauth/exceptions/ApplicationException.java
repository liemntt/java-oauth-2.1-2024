package dev.thanhliem.oauth.exceptions;

import dev.thanhliem.oauth.constants.ErrorCodes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends RuntimeException {

    private final ErrorCodes code;
    private final String detail;

    public ApplicationException(ErrorCodes code, String message) {
        super(message);
        this.code = code;
        this.detail = message;
    }

    public ApplicationException(ErrorCodes code, String message, String detail) {
        super(message);
        this.code = code;
        this.detail = detail;
    }
}