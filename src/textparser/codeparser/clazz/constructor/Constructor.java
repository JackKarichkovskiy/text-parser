/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.codeparser.clazz.constructor;

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
 * Class represents the constructor of class.
 * @author Karichkovskiy Yevhen
 */
public class Constructor implements Parsable{

    /**
     * Regex pattern for constructor validating.
     */
    private final Pattern constructorPattern = Regex.IS_CONSTRUCTOR.getPattern();
    
    /**
     * Regex pattern for code validating.
     */
    private final Pattern wordPattern = Regex.IS_IDENTIFIER.getPattern();
    
    /**
     * The string contains the origin constructor;
     */
    private String constructor;
    
    /**
     * The list of words and delimiters of constructor.
     */
    private final List<Parsable> wordsAndDelimiters = new ArrayList<>();
    
    public Constructor(String constructor) {
        this.constructor = checkMethod(constructor);
    }
    
    @Override
    public void parse() {
        Matcher matcher = wordPattern.matcher(constructor);

        while (matcher.find()) {
            Word word = new Word(matcher.group().trim());
            word.parse();
            wordsAndDelimiters.add(word);

            char delimiter = ' ';
            for (int i = matcher.end(); i < constructor.length() && Delimiter.isCodeDelimiter(delimiter = constructor.charAt(i)); i++) {
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

    private String checkMethod(String constructor) {
        return TextParserUtils.checkRegex(constructor, constructorPattern);
    }
    
    public static void main(String[] args) {
        Constructor constructor = new Constructor(("  public A(){\n"
                + "int i = 0;\n"
                + "for(int i = 0; i<10; i++);\n}  ").trim());
        constructor.parse();
        System.out.println(constructor.wordsAndDelimiters);
        System.out.println(constructor.compose());
        System.out.println(constructor.getWords());
    }    
}
