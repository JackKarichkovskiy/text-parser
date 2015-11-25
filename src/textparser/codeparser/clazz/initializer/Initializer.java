/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.codeparser.clazz.initializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import textparser.parser.AbstractParsable;
import textparser.parser.Parsable;
import textparser.symbol.delimiter.Delimiter;
import textparser.utils.TextParserUtils;
import textparser.utils.regex.Regex;
import textparser.word.Word;

/**
 * Class represents initializer of class.
 *
 * @author Karichkovskiy Yevhen
 */
public class Initializer extends AbstractParsable {

    /**
     * Regex pattern for initializer validating.
     */
    private final Pattern initializerPattern = Regex.IS_INITIALIZER.getPattern();
    
    /**
     * Regex pattern for word validating.
     */
    private final Pattern wordPattern = Regex.IS_IDENTIFIER.getPattern();

    public Initializer(String initializer) {
        super.originStr = checkInitializer(initializer);
    }

    @Override
    public void parse() {
        Matcher matcher = wordPattern.matcher(super.originStr);

        if (!super.originStr.isEmpty()) {
            super.parsedList.add(new Delimiter(super.originStr.charAt(0))); //{ - symbol added to list !!!!!!!
        }

        while (matcher.find()) {
            Word word = new Word(matcher.group().trim());
            word.parse();
            super.parsedList.add(word);

            char delimiter = ' ';
            for (int i = matcher.end(); i < super.originStr.length() && Delimiter.isCodeDelimiter(delimiter = super.originStr.charAt(i)); i++) {
                super.parsedList.add(new Delimiter(delimiter));
            }
        }
    }

    @Override
    public void pollOrderedWords(Queue<Word> queue) {
        queue = TextParserUtils.checkNotNull(queue);

        for (int i = 0; i < super.parsedList.size(); i++) {
            if (queue.isEmpty()) {
                break;
            }

            if (super.parsedList.get(i) instanceof Word) {
                super.parsedList.set(i, queue.poll());
            }
        }
    }

    private String checkInitializer(String initializer) {
        return TextParserUtils.checkRegex(initializer, initializerPattern);
    }

    public static void main(String[] args) {
        Initializer initializer = new Initializer(("  {\n"
                + "int i = 0;\n"
                + "for(int i = 0; i<10; i++);\n}  ").trim());
        initializer.parse();
        System.out.println(initializer.parsedList);
        System.out.println(initializer.compose());
        System.out.println(initializer.getWords());
    }
}
