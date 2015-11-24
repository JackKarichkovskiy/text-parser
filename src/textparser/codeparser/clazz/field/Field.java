/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.codeparser.clazz.field;

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
 * The class represents the field of class.
 * @author Karichkovskiy Yevhen
 */
public class Field implements Parsable{

    /**
     * Regex pattern for field validating.
     */
    private final Pattern fieldPattern = Regex.IS_FIELD.getPattern();
    
    /**
     * Regex pattern for word validating.
     */
    private final Pattern wordPattern = Regex.IS_IDENTIFIER.getPattern();
    
    /**
     * The origin field string.
     */
    private String field;
    
    /**
     * The list of words and delimiters in field.
     */
    private final List<Parsable> wordsAndDelimiters = new ArrayList<>();

    public Field(String field) {
        this.field = checkField(field);
    }
    
    @Override
    public void parse() {
        Matcher matcher = wordPattern.matcher(field);

        while (matcher.find()) {
            Word word = new Word(matcher.group().trim());
            word.parse();
            wordsAndDelimiters.add(word);

            char delimiter = ' ';
            for (int i = matcher.end(); i < field.length() && Delimiter.isCodeDelimiter(delimiter = field.charAt(i)); i++) {
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
    
    private String checkField(String field){
        return TextParserUtils.checkRegex(field, fieldPattern);
    }
    
    public static void main(String[] args) {
        Field field = new Field("  public  static int i = 10;  ".trim());
        field.parse();
        System.out.println(field.wordsAndDelimiters);
        System.out.println(field.compose());
        System.out.println(field.getWords());
    }
}
