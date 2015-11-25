/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.paragraph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import textparser.parser.AbstractParsable;
import textparser.sentence.Sentence;
import textparser.symbol.delimiter.Delimiter;
import textparser.utils.TextParserUtils;
import textparser.utils.regex.Regex;

/**
 * The class represents paragraph in text.
 *
 * @author Karichkovskiy Yevhen
 */
public class Paragraph extends AbstractParsable {

    /**
     * Regex pattern for sentence validating.
     */
    private final Pattern sentencePattern = Regex.IS_SENTENCE.getPattern();
    
    /**
     * Regex pattern for paragraph validating.
     */
    private final Pattern paragraphPattern = Regex.IS_PARAGRAPH.getPattern();

    public Paragraph(String paragraph) {
        super.originStr = TextParserUtils.checkRegex(paragraph, paragraphPattern);
    }

    @Override
    public void parse() {
        Matcher matcher = sentencePattern.matcher(super.originStr);

        super.parsedList.add(new Delimiter('\t'));

        while (matcher.find()) {
            Sentence sentence = new Sentence(matcher.group().trim());
            sentence.parse();
            super.parsedList.add(sentence);
            super.parsedList.add(new Delimiter(' '));
        }

        super.parsedList.add(new Delimiter('\n'));
    }

    @Override
    public String toString() {
        return "Paragraph{" + "paragraph=" + super.originStr + '}';
    }

    public static void main(String[] args) {
        String strPar = "\tIt's - my text.   Some   текст a little bit!    \tJack, do you want some text?! \n";
        Paragraph paragraph = new Paragraph(strPar);
        paragraph.parse();
        System.out.println(paragraph.parsedList);
        //System.out.println(strPar.trim());
        System.out.println(paragraph.getWords());
    }
}
