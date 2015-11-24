/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.codeparser.clazz.method;

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
 * Class represents method of class.
 * @author Karichkovskiy Yevhen
 */
public class Method implements Parsable{

    /**
     * Regex pattern for method validating.
     */
    private final Pattern methodPattern = Regex.IS_METHOD.getPattern();
    
    /**
     * Regex pattern for word validating.
     */
    private final Pattern wordPattern = Regex.IS_IDENTIFIER.getPattern();
    
    /**
     * The origin method string.
     */
    private String method;
    
    /**
     * List of words and delimiters of method.
     */
    private final List<Parsable> wordsAndDelimiters = new ArrayList<>();
    
    public Method(String method) {
        this.method = checkMethod(method);
    }
    
    @Override
    public void parse() {
        Matcher matcher = wordPattern.matcher(method);

        while (matcher.find()) {
            Word word = new Word(matcher.group().trim());
            word.parse();
            wordsAndDelimiters.add(word);

            char delimiter = ' ';
            for (int i = matcher.end(); i < method.length() && Delimiter.isCodeDelimiter(delimiter = method.charAt(i)); i++) {
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

    private String checkMethod(String method) {
        return TextParserUtils.checkRegex(method, methodPattern);
    }
    
    public static void main(String[] args) {
        Method method = new Method(("  public  static int i(){\n"
                + "int i = 0;\n"
                + "for(int i = 0; i<10; i++);\n}  ").trim());
        method.parse();
        System.out.println(method.wordsAndDelimiters);
        System.out.println(method.compose());
        System.out.println(method.getWords());
    }
    
}
