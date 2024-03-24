package dev.thanhliem.oauth.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import dev.thanhliem.oauth.constants.Constants;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Slf4j
public final class Utils {

    private Utils() {
        throw new IllegalStateException(Constants.UTILITY_CLASSES);
    }

    private static final JsonMapper JSON_MAPPER;

    static {
        JSON_MAPPER = JsonMapper.builder()
            .findAndAddModules()
            .configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, true)
            .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
            .build();
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

    public static boolean nonBlank(String str) {
        return !nullOrBlank(str);
    }

    public static boolean nullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean nonEmpty(Collection<?> collection) {
        return !nullOrEmpty(collection);
    }

    public static String toJson(Object object) {
        try {
            if (object == null) {
                return null;
            }
            return JSON_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.warn("[toJson] Cannot parse object. Error %s".formatted(e.getMessage()), e);
            return null;
        }
    }
}
