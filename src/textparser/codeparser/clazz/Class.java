/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.codeparser.clazz;

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
 * Class represents the parsed class.
 *
 * @author Karichkovskiy Yevhen
 */
public class Class implements Parsable {

    /**
     * Regex pattern for class validating.
     */
    private final Pattern classPattern = Regex.IS_CLASS.getPattern();
    
    /**
     * Regex pattern for class signature validating.
     */
    private final Pattern classSignaturePattern = Regex.IS_CLASS_SIGNATURE.getPattern();
    
    /**
     * Regex pattern for word validating.
     */
    private final Pattern wordPattern = Regex.IS_WORD.getPattern();
    
    /**
     * Array of different class parts validating.
     */
    private final Pattern[] classPatterns = {
        Regex.IS_FIELD.getPattern(),
        Regex.IS_INITIALIZER.getPattern(),
        Regex.IS_CONSTRUCTOR.getPattern(),
        Regex.IS_METHOD.getPattern(),};

    /**
     * The string that contains the origin class.
     */
    private String clazz;
    
    /**
     * The list of words and delimiters in class.
     */
    private final List<Parsable> wordsAndDelimiters = new ArrayList<>();

    public Class(String clazz) {
        this.clazz = checkClass(clazz);
    }

    @Override
    public void parse() {
        Matcher matcher = wordPattern.matcher(clazz);

        while (matcher.find()) {
            Word word = new Word(matcher.group().trim());
            word.parse();
            wordsAndDelimiters.add(word);

            char delimiter = ' ';
            for (int i = matcher.end(); i < clazz.length() && Delimiter.isCodeDelimiter(delimiter = clazz.charAt(i)); i++) {
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

    @Override
    public String toString() {
        return "Class{" + "clazz=" + clazz + '}';
    }

    /**
     * Method validates the class.
     * @param clazz - string with class
     * @return the input string if all is correct
     */
    private String checkClass(String clazz) {
        return TextParserUtils.checkRegex(clazz, classPattern);
    }

    public static void main(String[] args) {
        Class clazz = new Class(("public class \n A extends  B implements C,  D {\n"
                + "private static double a = 10.0;\n"
                + "{b = 3;}\n"
                + "{b = 3;}\n"
                + "private int b;\n"
                + "public A(int b){\n"
                + "setB(b);"
                + "}\n"
                + "public void setB(int b){\n"
                + "this.b = b;\n"
                + "}\n"
                + "}").trim());
        clazz.parse();
        System.out.println(clazz.wordsAndDelimiters);
        System.out.println(clazz.compose());
        System.out.println(clazz.getWords());
    }

}
