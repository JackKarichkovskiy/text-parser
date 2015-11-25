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
import textparser.parser.AbstractParsable;
import textparser.parser.Parsable;
import textparser.symbol.delimiter.Delimiter;
import textparser.utils.TextParserUtils;
import textparser.utils.regex.Regex;
import textparser.word.Word;

/**
 * Class represents the constructor of class.
 * @author Karichkovskiy Yevhen
 */
public class Constructor extends AbstractParsable{

    /**
     * Regex pattern for constructor validating.
     */
    private final Pattern constructorPattern = Regex.IS_CONSTRUCTOR.getPattern();
    
    /**
     * Regex pattern for code validating.
     */
    private final Pattern wordPattern = Regex.IS_IDENTIFIER.getPattern();
    
    public Constructor(String constructor) {
        super.originStr = checkMethod(constructor);
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
    public String compose() {
        StringBuilder strBuf = new StringBuilder();

        super.parsedList.stream().forEach((parseObj) -> {
            strBuf.append(parseObj.compose());
        });

        return strBuf.toString();
    }

    @Override
    public List<Word> getWords() {
        List<Word> result = new ArrayList<>();

        for (Parsable sentencePart : super.parsedList) {
            result.addAll(sentencePart.getWords());
        }

        return result;
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

    private String checkMethod(String constructor) {
        return TextParserUtils.checkRegex(constructor, constructorPattern);
    }
    
    public static void main(String[] args) {
        Constructor constructor = new Constructor(("  public A(){\n"
                + "int i = 0;\n"
                + "for(int i = 0; i<10; i++);\n}  ").trim());
        constructor.parse();
        System.out.println(constructor.parsedList);
        System.out.println(constructor.compose());
        System.out.println(constructor.getWords());
    }    
}
