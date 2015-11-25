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
import textparser.parser.AbstractParsable;
import textparser.parser.Parsable;
import textparser.symbol.delimiter.Delimiter;
import textparser.utils.TextParserUtils;
import textparser.utils.regex.Regex;
import textparser.word.Word;

/**
 * The class represents the field of class.
 * @author Karichkovskiy Yevhen
 */
public class Field extends AbstractParsable{

    /**
     * Regex pattern for field validating.
     */
    private final Pattern fieldPattern = Regex.IS_FIELD.getPattern();
    
    /**
     * Regex pattern for word validating.
     */
    private final Pattern wordPattern = Regex.IS_IDENTIFIER.getPattern();

    public Field(String field) {
        super.originStr = checkField(field);
    }
    
    @Override
    public void parse() {
        Matcher matcher = wordPattern.matcher(super.originStr);

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
    
    private String checkField(String field){
        return TextParserUtils.checkRegex(field, fieldPattern);
    }
    
    public static void main(String[] args) {
        Field field = new Field("  public  static int i = 10;  ".trim());
        field.parse();
        System.out.println(field.parsedList);
        System.out.println(field.compose());
        System.out.println(field.getWords());
    }
}
