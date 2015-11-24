/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.paragraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import textparser.parser.Parsable;
import textparser.sentence.Sentence;
import textparser.symbol.delimiter.Delimiter;
import textparser.utils.TextParserUtils;
import textparser.utils.regex.Regex;
import textparser.word.Word;

/**
 * The class represents paragraph in text.
 *
 * @author Karichkovskiy Yevhen
 */
public class Paragraph implements Parsable {

    /**
     * Regex pattern for sentence validating.
     */
    private final Pattern sentencePattern = Regex.IS_SENTENCE.getPattern();
    
    /**
     * Regex pattern for paragraph validating.
     */
    private final Pattern paragraphPattern = Regex.IS_PARAGRAPH.getPattern();

    /**
     * The origin paragraph string.
     */
    private final String paragraph;

    /**
     * The list of sentences.
     */
    private final List<Parsable> sentences = new ArrayList<>();

    public Paragraph(String paragraph) {
        this.paragraph = TextParserUtils.checkRegex(paragraph, paragraphPattern);
    }

    @Override
    public void parse() {
        Matcher matcher = sentencePattern.matcher(paragraph);

        sentences.add(new Delimiter('\t'));

        while (matcher.find()) {
            Sentence sentence = new Sentence(matcher.group().trim());
            sentence.parse();
            sentences.add(sentence);
            sentences.add(new Delimiter(' '));
        }

        sentences.add(new Delimiter('\n'));
    }

    @Override
    public String compose() {
        if (sentences.isEmpty()) {
            return "";
        }

        StringBuilder strBuf = new StringBuilder();
        sentences.stream().forEach((parseObj) -> {
            strBuf.append(parseObj.compose());
        });

        return strBuf.toString();
    }

    @Override
    public List<Word> getWords() {
        List<Word> result = new ArrayList<>();

        for (Parsable sentence : sentences) {
            result.addAll(sentence.getWords());
        }

        return result;
    }

    @Override
    public void pollOrderedWords(Queue<Word> queue) {
        queue = TextParserUtils.checkNotNull(queue);

        for (Parsable sentence : sentences) {
            sentence.pollOrderedWords(queue);
        }
    }

    @Override
    public String toString() {
        return "Paragraph{" + "paragraph=" + paragraph + '}';
    }

    public static void main(String[] args) {
        String strPar = "\tIt's - my text.   Some   текст a little bit!    \tJack, do you want some text?! \n";
        Paragraph paragraph = new Paragraph(strPar);
        paragraph.parse();
        System.out.println(paragraph.sentences);
        //System.out.println(strPar.trim());
        System.out.println(paragraph.getWords());
    }
}
