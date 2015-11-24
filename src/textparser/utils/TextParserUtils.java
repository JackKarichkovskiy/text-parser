/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import textparser.localization.Messages;

/**
 *
 * @author Karichkovskiy Yevhen
 */
public class TextParserUtils {

    /**
     * Method checks the object by null equality.
     *
     * @param <T> - type of object
     * @param obj - object that needs to be checked
     * @return the origin value
     * @throws IllegalArgumentException if (object == null)
     */
    public static final <T> T checkNotNull(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException(Messages.NULL_OBJECT_MESSAGE);
        }

        return obj;
    }

    /**
     * Validates string by some regex pattern.
     * @param str - string that needs to be validated
     * @param pattern - regex pattern
     * @return origin string if validated
     * @throws IllegalArgumentException - if isn't validated
     */
    public static final String checkRegex(String str, Pattern pattern) {
        checkNotNull(str);
        checkNotNull(pattern);

        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches()) {
            System.out.println(str);
            throw new IllegalArgumentException(String.format(Messages.NOT_VALID_STRING_MESSAGE));
        }

        return str;
    }
}
