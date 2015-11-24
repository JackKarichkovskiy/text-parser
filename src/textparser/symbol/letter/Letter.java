/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.symbol.letter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import textparser.localization.Messages;
import textparser.symbol.Symbol;
import textparser.utils.regex.Regex;

/**
 * Class that represents letter.
 *
 * @author Karichkovskiy Yevhen
 */
public final class Letter extends Symbol {

    /**
     * Regex pattern for letter validating.
     */
    private static final Pattern LETTER_PATTERN = Regex.IS_LETTER.getPattern();

    public Letter(char character) {
        super(character);
    }

    /**
     * Validates the letter.
     * @param character - input letter
     * @return true - if validated, false - otherwise
     */
    public static boolean isLetter(char character) {
        Matcher matcher = LETTER_PATTERN.matcher("" + character);
        return matcher.matches();
    }

    @Override
    protected char checkSymbol(char character) {
        if (!isLetter(character)) {
            throw new IllegalArgumentException(Messages.NOT_LETTER_MESSAGE);
        }

        return character;
    }

}
