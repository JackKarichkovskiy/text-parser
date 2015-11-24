/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.utils.regex;

import java.util.regex.Pattern;

/**
 * Enumeration that contains all regex patterns.
 *
 * @author Karichkovskiy Yevhen
 */
public enum Regex {

    // ---------Sentence regexes---------
    IS_TAB("\\t"),
    IS_SPACE_DELIMITER("( |" + IS_TAB.regex + ")"),
    IS_LINE_END("\\n"),
    IS_PARAGRAPH_END(IS_LINE_END.regex),
    IS_PARAGRAPH_START(IS_TAB.regex),
    IS_LETTER("[\\wА-Яа-я]"),
    IS_WORD_DELIMITER("['\\-]"),
    IS_SENTENCE_DELIMITER("([,\\-:;\"\\(\\)]|" + IS_SPACE_DELIMITER.regex + ")"),
    IS_SENTENCE_END_DELIMITER("[.?!]"),
    IS_TEXT_DELIMITER(
            "(" + IS_SENTENCE_DELIMITER.regex
            + "|" + IS_WORD_DELIMITER.regex
            + "|" + IS_SENTENCE_END_DELIMITER.regex
            + "|" + IS_PARAGRAPH_END.regex
            + "|" + IS_PARAGRAPH_START.regex
            + ")"
    ),
    IS_SIMPLE_WORD("(" + IS_LETTER.regex + "+)"),
    IS_WORD_WITH_EXTENSION("(" + IS_SIMPLE_WORD.regex + "?(\\." + IS_SIMPLE_WORD.regex + ")+)"),
    IS_WORD("(" + IS_SIMPLE_WORD.regex + "(" + IS_WORD_DELIMITER.regex + IS_SIMPLE_WORD.regex + ")?" + ")"),
    IS_WORD_WITH_CAPITAL("([A-ZА-Я]" + IS_WORD.regex + "?" + ")"),
    IS_SENTENCE(
            "(" + IS_WORD_WITH_CAPITAL.regex + "(" + IS_SENTENCE_DELIMITER.regex + "+" + IS_WORD.regex + ")*"
            + IS_SENTENCE_END_DELIMITER.regex + "+"
            + ")"
    ),
    IS_PARAGRAPH("(" + IS_PARAGRAPH_START.regex + "+"
            + "(" + IS_SPACE_DELIMITER.regex + "*" + IS_SENTENCE.regex + IS_SPACE_DELIMITER.regex + "*" + ")+"
            + IS_PARAGRAPH_END.regex + "+)"),
    // ----------Code regexes--------------
    IS_CODE_START(IS_TAB.regex),
    IS_CODE_END(IS_LINE_END.regex),
    IS_EXPRESSION_DELIMITER(";"),
    IS_OPEN_BLOCK_CODE_DELIMITERS("\\{"),
    IS_CLOSE_BLOCK_CODE_DELIMITERS("\\}"),
    IS_CODE_SPACE_DELIMITER("(" + "\\n" + "|" + IS_SPACE_DELIMITER.regex + ")"),
    IS_CODE_WORDS_DELIMITER("([,()=+*^/<>?:.\\[\\]|&!\\-'\"]|"
            + IS_CODE_SPACE_DELIMITER.regex
            + ")"
    ),
    IS_CODE_DELIMITER("(" + IS_CODE_WORDS_DELIMITER.regex + "|"
            + IS_EXPRESSION_DELIMITER.regex + "|"
            + IS_OPEN_BLOCK_CODE_DELIMITERS.regex + "|"
            + IS_CLOSE_BLOCK_CODE_DELIMITERS.regex
            + ")"),
    IS_MODIFIER("(public|protected|private|static|final|abstract)"),
    IS_IDENTIFIER(IS_SIMPLE_WORD.regex),
    IS_TYPE(IS_IDENTIFIER.regex),
    IS_TYPED_IDENTIFIER("(" + IS_TYPE.regex + IS_CODE_SPACE_DELIMITER.regex + "+" + IS_IDENTIFIER.regex + ")"),
    IS_EXPRESSION("((" + IS_CODE_WORDS_DELIMITER.regex + "*" + IS_IDENTIFIER.regex + IS_CODE_WORDS_DELIMITER.regex + "*)+" + IS_EXPRESSION_DELIMITER.regex + ")"),
    IS_INITIALIZER("((static)?" + IS_CODE_WORDS_DELIMITER.regex + "*" + IS_OPEN_BLOCK_CODE_DELIMITERS.regex + IS_CODE_WORDS_DELIMITER.regex + "*"
            + "(" + IS_EXPRESSION.regex + IS_CODE_WORDS_DELIMITER.regex + "*" + ")*"
            + IS_CLOSE_BLOCK_CODE_DELIMITERS.regex
            + ")"
    ),
    IS_FIELD("((" + IS_MODIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "+)*"
            + IS_TYPE.regex + IS_CODE_SPACE_DELIMITER.regex + "+"
            + IS_IDENTIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "*"
            + "(" + "=" + "(" + IS_CODE_WORDS_DELIMITER.regex + "*" + IS_IDENTIFIER.regex + IS_CODE_WORDS_DELIMITER.regex + "*)+" + ")?"
            + IS_EXPRESSION_DELIMITER.regex + ")"
    ),
    IS_METHOD("((" + IS_MODIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "+)*" //MODIFIER
            + "(" + IS_TYPE.regex + "|" + "void" + ")" + IS_CODE_SPACE_DELIMITER.regex + "+" //TYPE
            + IS_IDENTIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "*" //NAME
            + "\\(" + "(" + IS_TYPED_IDENTIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "*" //ARGUMENTS
            + "(," + IS_CODE_SPACE_DELIMITER.regex + "*" + IS_TYPED_IDENTIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "*" + ")*" + ")?" + "\\)" + IS_CODE_SPACE_DELIMITER.regex + "*"
            + "(" + "throws" + IS_CODE_SPACE_DELIMITER.regex + "*" + IS_IDENTIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "*" //THROWS
            + "(," + IS_CODE_SPACE_DELIMITER.regex + "*" + IS_IDENTIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "*" + ")*" + ")?"
            + "((" + IS_OPEN_BLOCK_CODE_DELIMITERS.regex
            + IS_CODE_SPACE_DELIMITER.regex + "*"
            + "(" + IS_EXPRESSION.regex + IS_CODE_SPACE_DELIMITER.regex + "*" + ")*" //BODY
            + IS_CLOSE_BLOCK_CODE_DELIMITERS.regex + ")|" + "(" + IS_EXPRESSION_DELIMITER.regex + ")" + ")" //expression end - if abstract
            + ")"
    ),
    IS_CONSTRUCTOR("((" + IS_MODIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "+)*" //MODIFIER
            + IS_IDENTIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "*" //NAME
            + "\\(" + "(" + IS_TYPED_IDENTIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "*" //ARGUMENTS
            + "(," + IS_CODE_SPACE_DELIMITER.regex + "*" + IS_TYPED_IDENTIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "*" + ")*" + ")?" + "\\)" + IS_CODE_SPACE_DELIMITER.regex + "*"
            + "(" + "throws" + IS_CODE_SPACE_DELIMITER.regex + "*" + IS_IDENTIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "*" //THROWS
            + "(," + IS_CODE_SPACE_DELIMITER.regex + "*" + IS_IDENTIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "*" + ")*" + ")?"
            + IS_OPEN_BLOCK_CODE_DELIMITERS.regex
            + "(" + IS_CODE_SPACE_DELIMITER.regex + "*" + IS_EXPRESSION.regex + IS_CODE_SPACE_DELIMITER.regex + "*" + ")*" //BODY
            + IS_CLOSE_BLOCK_CODE_DELIMITERS.regex
            + ")"
    ),
    IS_CLASS_SIGNATURE("((" + IS_MODIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "+)*" //MODIFIER
            + "class" + IS_CODE_SPACE_DELIMITER.regex + "+"
            + IS_IDENTIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "*" //NAME
            + "(" + IS_CODE_SPACE_DELIMITER.regex + "+" + "extends" + IS_CODE_SPACE_DELIMITER.regex + "+"
            + IS_IDENTIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "*" + ")?" //EXTENDS
            + "(" + IS_CODE_SPACE_DELIMITER.regex + "+" + "implements" + IS_CODE_SPACE_DELIMITER.regex + "+"
            + IS_IDENTIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "*" + "(," + IS_CODE_SPACE_DELIMITER.regex + "*"
            + IS_IDENTIFIER.regex + IS_CODE_SPACE_DELIMITER.regex + "*" + ")*" + ")?)" //IMPLEMENTS)
    ),
    IS_CLASS("(" + IS_CLASS_SIGNATURE.regex
            + IS_OPEN_BLOCK_CODE_DELIMITERS.regex
            + IS_CODE_SPACE_DELIMITER.regex + "*"
            + "((" + IS_FIELD.regex + "|" + IS_CONSTRUCTOR.regex + "|" + IS_METHOD.regex + "|" + IS_INITIALIZER.regex + ")"
            + IS_CODE_SPACE_DELIMITER.regex + "*" + ")*"
            + IS_CLOSE_BLOCK_CODE_DELIMITERS.regex
            + ")"
    ),
    IS_CODE("("
            + IS_CODE_START.regex + "*"
            + "(" + IS_CLASS.regex + IS_CODE_SPACE_DELIMITER.regex + "*" + ")+"
            + IS_CODE_END.regex
            + ")"
    ),;

    private final String regex;

    private final Pattern pattern;

    Regex(String regex) {
        this.regex = regex;
        this.pattern = Pattern.compile(regex);
    }

    public String getRegex() {
        return regex;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
