package dev.thanhliem.oauth.constants;

public class Endpoints {

    private Endpoints() {
        throw new IllegalStateException(Constants.UTILITY_CLASSES);
    }

    public static final String API_PREFIX = "/api";
    public static final String ALL_API_PATTERN = API_PREFIX + "/**";
    public static final String CURRENT_VERSION = "1";
    public static final String KEY_VERSION = "Api-versioning";
    public static final String HEADER_VERSION = KEY_VERSION + "=" + CURRENT_VERSION;

    public static class UserApi {
        private UserApi() {
            throw new IllegalStateException(Constants.UTILITY_CLASSES);
        }
        public static final String GET_ALL_USERS = API_PREFIX + "/users";
        public static final String GET_USER_BY_ID = GET_ALL_USERS + "/{id}";
        public static final String SIGN_UP = GET_ALL_USERS + "/signup";
        public static final String SIGNING = GET_ALL_USERS + "/signing";
    }

    public static class InfoApi {

        private InfoApi() {
            throw new IllegalStateException(Constants.UTILITY_CLASSES);
        }
        public static final String PATH = API_PREFIX + "/info";
    }
}
