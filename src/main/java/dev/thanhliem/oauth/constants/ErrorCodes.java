package dev.thanhliem.oauth.constants;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    BAD_REQUEST,
    BAD_CREDENTIALS,
    UNAUTHORIZED,
    INVALID_PAYLOAD,
    INVALID_PARAMS,
    INVALID_VERSION,
    PASSWORD_MISMATCH,
    USER_NOT_FOUND,
    USERNAME_EXISTED,
    ROLES_NOT_FOUND,
    APPLICATION_NOT_FOUND,
    CANNOT_CREATE_APPLICATION
}
