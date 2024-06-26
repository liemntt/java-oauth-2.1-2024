package dev.thanhliem.oauth.exceptions;

import dev.thanhliem.oauth.constants.Constants;
import dev.thanhliem.oauth.constants.Endpoints;
import dev.thanhliem.oauth.constants.ErrorCodes;
import dev.thanhliem.oauth.constants.ErrorMessages;
import dev.thanhliem.oauth.models.responses.GlobalErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setTimestamp(ZonedDateTime.now());
        errorResponse.setPath(request.getDescription(false));
        var versionHeader = request.getHeader(Constants.Headers.KEY_VERSION);
        if (!Endpoints.CURRENT_VERSION.equalsIgnoreCase(versionHeader)) {
            errorResponse.setError(ErrorMessages.INVALID_VERSION);
            errorResponse.setMessage("Invalid api version");
            errorResponse.setDetail("Invalid api version %s. Current version %s".formatted(versionHeader, Endpoints.CURRENT_VERSION));
        } else {
            errorResponse.setError(ErrorMessages.RESOURCES_NOT_FOUND);
            errorResponse.setMessage("Resource not found");
            errorResponse.setMessage(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GlobalErrorResponse> handleBadRequestException(BadRequestException ex, WebRequest request) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setTimestamp(ZonedDateTime.now());
        errorResponse.setPath(request.getDescription(false));
        errorResponse.setError(ex.getCode().name());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDetail(ex.getDetail());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAppException(Exception ex, WebRequest request) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setTimestamp(ZonedDateTime.now());
        errorResponse.setPath(request.getDescription(false));
        errorResponse.setError(ErrorCodes.BAD_REQUEST.name());
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleAppException(ex, request);
    }
}
