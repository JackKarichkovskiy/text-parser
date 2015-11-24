/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.symbol.delimiter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import textparser.localization.Messages;
import textparser.symbol.Symbol;
import textparser.utils.regex.Regex;

/**
 * Class represents the delimiter in text.
 *
 * @author Karichkovskiy Yevhen
 */
public final class Delimiter extends Symbol {

    /**
     * Regex pattern for delimiter validating.
     */
    private static final Pattern DELIMITER_PATTERN = Regex.IS_TEXT_DELIMITER.getPattern();
    
    /**
     * Regex pattern for code delimiter validating.
     */
    private static final Pattern CODE_DELIMITER_PATTERN = Regex.IS_CODE_DELIMITER.getPattern();

    public Delimiter(char character) {
        super(character);
    }

    /**
     * Validates the delimiter.
     * @param character - input character
     * @return true - if validated, false - otherwise
     */
    public static boolean isDelimiter(char character) {
        Matcher matcher = DELIMITER_PATTERN.matcher("" + character);
        return matcher.matches();
    }

    /**
     * Validates the code delimiter.
     * @param character - input character
     * @return true - if validated, false - otherwise
     */
    public static boolean isCodeDelimiter(char character) {
        Matcher matcher = CODE_DELIMITER_PATTERN.matcher("" + character);
        return matcher.matches();
    }

    @Override
    protected char checkSymbol(char character) {
        if (!isDelimiter(character) && !isCodeDelimiter(character)) {
            throw new IllegalArgumentException(String.format(Messages.NOT_DELIMITER_MESSAGE, character));
        }

        return character;
    }
}
