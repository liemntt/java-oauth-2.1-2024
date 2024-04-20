package dev.thanhliem.oauth.constants;

public final class ErrorMessages {

    private ErrorMessages() {
        throw new IllegalStateException(Constants.UTILITY_CLASSES);
    }

    public static final String THE_USER_IS_NOT_FOUND = "The user %s is not found";

    public static final String INVALID_VERSION = "INVALID_VERSION";
    public static final String RESOURCES_NOT_FOUND = "RESOURCES_NOT_FOUND";
    public static final String INVALID_PASSWORD = "Invalid password";
    public static final String INVALID_PAYLOAD = "Invalid payload";
}
