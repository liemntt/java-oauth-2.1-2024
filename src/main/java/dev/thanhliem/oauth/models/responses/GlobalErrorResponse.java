package dev.thanhliem.oauth.models.responses;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class GlobalErrorResponse {

    private ZonedDateTime timestamp;
    private String error;
    private String message;
    private String detail;
    private String path;
}
