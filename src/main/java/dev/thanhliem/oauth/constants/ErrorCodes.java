package dev.thanhliem.oauth.constants;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    BAD_REQUEST,
    UNAUTHORIZED,
    INVALID_PAYLOAD,
    INVALID_VERSION,
    PASSWORD_MISMATCH,
    USER_NOT_FOUND,
    USERNAME_EXISTED,
    ROLES_NOT_FOUND,
    ;
}