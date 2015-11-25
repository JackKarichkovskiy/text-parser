/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.codeparser.clazz;

import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import textparser.parser.AbstractParsable;
import textparser.symbol.delimiter.Delimiter;
import textparser.utils.TextParserUtils;
import textparser.utils.regex.Regex;
import textparser.word.Word;

/**
 * Class represents the parsed class.
 *
 * @author Karichkovskiy Yevhen
 */
public class Class extends AbstractParsable {

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

    public Class(String clazz) {
        super.originStr = checkClass(clazz);
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

    @Override
    public String toString() {
        return "Class{" + "clazz=" + super.originStr + '}';
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
        System.out.println(clazz.parsedList);
        System.out.println(clazz.compose());
        System.out.println(clazz.getWords());
    }

}
