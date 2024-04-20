package dev.thanhliem.oauth.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import dev.thanhliem.oauth.constants.Constants;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.IntStream;

@Slf4j
public final class Utils {

    private Utils() {
        throw new IllegalStateException(Constants.UTILITY_CLASSES);
    }

    private static final JsonMapper JSON_MAPPER;

    public static final Random BASIC_RANDOM = new Random();

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
            log.warn("[toJson] Cannot parse object %s. Error %s".formatted(object.toString(), e.getMessage()), e);
            return null;
        }
    }

    public static String generatePassword(int length) {
        if (length < Constants.PASSWORD_LENGTH_MIN) {
            length = Constants.PASSWORD_LENGTH_MIN;
        }
        if (length > Constants.PASSWORD_LENGTH_MAX) {
            length = Constants.PASSWORD_LENGTH_MAX;
        }
        StringBuilder passwordBuilder = new StringBuilder(length);
        int numberChars = length % 4;
        String randomLowerChar = generateRandomString(Constants.CHARACTERS_LOWER_CASE, numberChars);
        passwordBuilder.append(randomLowerChar);

        String randomUpperChar = generateRandomString(Constants.CHARACTERS_UPPER_CASE, numberChars);
        passwordBuilder.append(randomUpperChar);

        String randomDigits = generateRandomString(Constants.DIGITS, numberChars);
        passwordBuilder.append(randomDigits);

        String randomSpecial = generateRandomString(Constants.OTHER_SPECIAL, numberChars);
        passwordBuilder.append(randomSpecial);

        String randomAllowed = generateRandomString(Constants.PASSWORD_ALLOWED, length - passwordBuilder.length());
        if (nonEmpty(randomAllowed)) {
            passwordBuilder.append(randomAllowed);
        }
        List<String> chars = Arrays.asList(passwordBuilder.toString().split(""));
        Collections.shuffle(chars);
        return String.join("", chars);
    }

    public static String generateRandomString(String input, int number) {
        if (nullOrBlank(input) || number < 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        if (number == 0) {
            return "";
        }
        StringBuilder randomStrBuilder = new StringBuilder(number);
        IntStream.range(0, number).forEach(i -> {
            var position = BASIC_RANDOM.nextInt(0, input.length());
            randomStrBuilder.append(input.charAt(position));
        });
        return randomStrBuilder.toString();
    }
}
