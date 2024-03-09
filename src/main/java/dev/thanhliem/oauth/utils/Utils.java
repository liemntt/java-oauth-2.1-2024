package dev.thanhliem.oauth.utils;

import dev.thanhliem.oauth.constants.Constants;

public final class Utils {

    private Utils() {
        throw new IllegalStateException(Constants.UTILITY_CLASSES);
    }

    public static boolean nullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean nonEmpty(String str) {
        return !nullOrEmpty(str);
    }

    public static boolean nullOrBlank(String str) {
        return str == null || str.isBlank();
    }
}
