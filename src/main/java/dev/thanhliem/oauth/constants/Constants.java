package dev.thanhliem.oauth.constants;

public final class Constants {

    private Constants() {
        throw new IllegalStateException(UTILITY_CLASSES);
    }

    public static final String UTILITY_CLASSES = "Utility classes";

    public static final String APP_NAME = "OAuth";

    public static class Roles {
        private Roles() {
            throw new IllegalStateException(UTILITY_CLASSES);
        }

        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";
    }

    public static class Headers {
        private Headers() {
            throw new IllegalStateException(UTILITY_CLASSES);
        }

        public static final String AUTHORIZATION = "Authorization";

        public static final String KEY_VERSION = "X-Api-Versioning";
    }

    public static final String CHARACTERS_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String CHARACTERS_UPPER_CASE = CHARACTERS_LOWER_CASE.toUpperCase();
    public static final String DIGITS = "0123456789";
    public static final String OTHER_PUNCTUATION = "!@#&()–[{}]:;',?/*";
    public static final String OTHER_SYMBOL = "~$^+=<>";
    public static final String OTHER_SPECIAL = OTHER_PUNCTUATION + OTHER_SYMBOL;
    public static final String PASSWORD_ALLOWED = CHARACTERS_LOWER_CASE + CHARACTERS_UPPER_CASE + DIGITS + OTHER_SPECIAL;
    public static final int PASSWORD_LENGTH_MIN = 12;
    public static final int PASSWORD_LENGTH_MAX = 50;
}
