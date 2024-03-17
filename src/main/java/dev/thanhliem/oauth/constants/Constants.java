package dev.thanhliem.oauth.constants;

public final class Constants {

    private Constants() {
        throw new IllegalStateException(UTILITY_CLASSES);
    }

    public static final String UTILITY_CLASSES = "Utility classes";

    public static class Roles {
        private Roles() {
            throw new IllegalStateException(UTILITY_CLASSES);
        }

        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";
    }
}
