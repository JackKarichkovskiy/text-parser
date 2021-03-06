/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.codeparser;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import textparser.parser.Parsable;
import textparser.utils.TextParserUtils;
import textparser.utils.regex.Regex;
import textparser.word.Word;
import textparser.codeparser.clazz.Class;
import textparser.parser.AbstractParsable;

/**
 * Class represents the code part of the text.
 *
 * @author Karichkovskiy Yevhen
 */
public class Code extends AbstractParsable {

    /**
     * Regex pattern for code validating.
     */
    private final Pattern codePattern = Regex.IS_CODE.getPattern();
    
    /**
     * Regex pattern for class validating.
     */
    private final Pattern classPattern = Regex.IS_CLASS.getPattern();

    public Code(String code) {
        super.originStr = TextParserUtils.checkRegex(code, codePattern);
    }

    @Override
    public void parse() {
        Matcher matcher = classPattern.matcher(super.originStr);

        while (matcher.find()) {
            Class clazz = new Class(matcher.group().trim());
            clazz.parse();
            super.parsedList.add(clazz);
        }

    }

    @Override
    public String compose() {
        if (super.parsedList.isEmpty()) {
            return "";
        }

        StringBuilder strBuf = new StringBuilder();

        super.parsedList.stream().forEach((parseObj) -> {
            strBuf.append(parseObj.compose()).append("\n");
        });

        return strBuf.toString();
    }

    @Override
    public String toString() {
        return "Code{" + "code=" + super.originStr + '}';
    }

    public static void main(String[] args) {
        Code code = new Code("public class B extends A{{int a = 0;}}\n" + "public class \n A extends  B implements C,  D {\n"
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
                + "}\n");
        code.parse();
        System.out.println(code.parsedList);
        System.out.println(code.compose());
        System.out.println(code.getWords());
    }
}
