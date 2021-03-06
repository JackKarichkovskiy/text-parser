/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.parser;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import textparser.codeparser.Code;
import textparser.paragraph.Paragraph;
import textparser.utils.TextParserUtils;
import textparser.utils.regex.Regex;
import textparser.word.Word;

/**
 * The class that parses the text.
 * @author Karichkovskiy Yevhen
 */
public class TextParser extends AbstractParsable {

    /**
     * Regex pattern for paragraph validating.
     */
    private final Pattern paragraphPattern = Regex.IS_PARAGRAPH.getPattern();
    
    /**
     * Regex pattern for code validating.
     */
    private final Pattern codePattern = Regex.IS_CODE.getPattern();

    public TextParser(String text) {
        super.originStr = TextParserUtils.checkNotNull(text);
    }

    @Override
    public void parse() {
        Matcher paragraphMatcher = paragraphPattern.matcher(super.originStr);
        Matcher codeMatcher = codePattern.matcher(super.originStr);

        int textIndex = 0;
        int paragraphIndex = paragraphMatcher.find(textIndex) ? paragraphMatcher.start() : Integer.MAX_VALUE;
        int codeIndex = codeMatcher.find(textIndex) ? codeMatcher.start() : Integer.MAX_VALUE;

        while (paragraphMatcher.find(textIndex) && codeMatcher.find(textIndex)) {
            if (paragraphMatcher.find(textIndex)
                    && (paragraphIndex = paragraphMatcher.start()) < codeIndex) {
                String paragraphStr = paragraphMatcher.group();
                Paragraph paragraph = new Paragraph(paragraphStr);
                paragraph.parse();
                super.parsedList.add(paragraph);
                textIndex = paragraphMatcher.end();
                continue;
            }

            if (codeMatcher.find(textIndex)
                    && (codeIndex = codeMatcher.start()) < paragraphIndex) {
                String codeStr = codeMatcher.group();
                Code code = new Code(codeStr);
                code.parse();
                super.parsedList.add(code);
                textIndex = codeMatcher.end();
                continue;
            }
        }

        while (paragraphMatcher.find(textIndex)) {
            String paragraphStr = paragraphMatcher.group();
            Paragraph paragraph = new Paragraph(paragraphStr);
            paragraph.parse();
            super.parsedList.add(paragraph);
            textIndex = paragraphMatcher.end();
        }

        while (codeMatcher.find(textIndex)) {
            String codeStr = codeMatcher.group();
            Code code = new Code(codeStr);
            code.parse();
            super.parsedList.add(code);
            textIndex = codeMatcher.end();
        }

    }

    public Queue<Word> sortByCharCountInWord(char character) {
        List<Word> allWords = getWords();

        if (allWords.isEmpty()) {
            return new PriorityQueue<>();
        }

        PriorityQueue<Word> orderedWords = new PriorityQueue<>(allWords.size(), (Word word1, Word word2) -> {
            int diff = word1.getCharCountInWord(character) - word2.getCharCountInWord(character);
            if (diff != 0) {
                return -diff;
            } else {
                return word1.getWord().compareTo(word2.getWord());
            }
        });
        orderedWords.addAll(allWords);

        pollOrderedWords(orderedWords);

        return orderedWords;
    }

    public static void main(String[] args) {
        TextParser textParser = new TextParser(
                "\tThis is a text with some code. Another sentence.\n"
                + "\tSome Text!!!\n"
                + "Some code dsfj lkasd sd jlkdsa.sjdkafj l\n"
                + "\tТекст, снова...\npublic class A{}\n");
        textParser.parse();
        System.out.println(textParser.parsedList);
        System.out.println(textParser.getWords());
        Queue<Word> orderedWords = textParser.sortByCharCountInWord('t');
        System.out.println(orderedWords);
        while (!orderedWords.isEmpty()) {
            System.out.print(orderedWords.poll().getWord() + " ");
        }
    }
}
