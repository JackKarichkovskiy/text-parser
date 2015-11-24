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
public class Initializer implements Parsable {

    /**
     * Regex pattern for initializer validating.
     */
    private final Pattern initializerPattern = Regex.IS_INITIALIZER.getPattern();
    
    /**
     * Regex pattern for word validating.
     */
    private final Pattern wordPattern = Regex.IS_IDENTIFIER.getPattern();

    /**
     * The origin initializer string.
     */
    private String initializer;
    
    /**
     * The list of words and delimiters of initializer.
     */
    private final List<Parsable> wordsAndDelimiters = new ArrayList<>();

    public Initializer(String initializer) {
        this.initializer = checkInitializer(initializer);
    }

    @Override
    public void parse() {
        Matcher matcher = wordPattern.matcher(initializer);

        if (!initializer.isEmpty()) {
            wordsAndDelimiters.add(new Delimiter(initializer.charAt(0))); //{ - symbol added to list !!!!!!!
        }

        while (matcher.find()) {
            Word word = new Word(matcher.group().trim());
            word.parse();
            wordsAndDelimiters.add(word);

            char delimiter = ' ';
            for (int i = matcher.end(); i < initializer.length() && Delimiter.isCodeDelimiter(delimiter = initializer.charAt(i)); i++) {
                wordsAndDelimiters.add(new Delimiter(delimiter));
            }
        }
    }

    @Override
    public String compose() {
        StringBuilder strBuf = new StringBuilder();

        wordsAndDelimiters.stream().forEach((parseObj) -> {
            strBuf.append(parseObj.compose());
        });

        return strBuf.toString();
    }

    @Override
    public List<Word> getWords() {
        List<Word> result = new ArrayList<>();

        for (Parsable sentencePart : wordsAndDelimiters) {
            result.addAll(sentencePart.getWords());
        }

        return result;
    }

    @Override
    public void pollOrderedWords(Queue<Word> queue) {
        queue = TextParserUtils.checkNotNull(queue);

        for (int i = 0; i < wordsAndDelimiters.size(); i++) {
            if (queue.isEmpty()) {
                break;
            }

            if (wordsAndDelimiters.get(i) instanceof Word) {
                wordsAndDelimiters.set(i, queue.poll());
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
        System.out.println(initializer.wordsAndDelimiters);
        System.out.println(initializer.compose());
        System.out.println(initializer.getWords());
    }
}
