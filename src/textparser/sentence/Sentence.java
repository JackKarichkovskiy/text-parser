/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.sentence;

import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import textparser.parser.AbstractParsable;
import textparser.symbol.delimiter.Delimiter;
import textparser.utils.TextParserUtils;
import textparser.utils.regex.Regex;
import textparser.word.Word;

/**
 * The class that represents sentence in text.
 *
 * @author Karichkovskiy Yevhen
 */
public class Sentence extends AbstractParsable {

    /**
     * Regex pattern for sentence validating.
     */
    private final Pattern sentencePattern = Regex.IS_SENTENCE.getPattern();
    
    /**
     * Regex pattern for word validating.
     */
    private final Pattern wordPattern = Regex.IS_WORD.getPattern();

    public Sentence(String sentence) {
        super.originStr = checkSentence(sentence);
    }

    @Override
    public void parse() {
        Matcher matcher = wordPattern.matcher(super.originStr);

        while (matcher.find()) {
            Word word = new Word(matcher.group().trim());
            word.parse();
            super.parsedList.add(word);

            char delimiter = ' ';
            for (int i = matcher.end(); i < super.originStr.length() && Delimiter.isDelimiter(delimiter = super.originStr.charAt(i)); i++) {
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

    private String checkSentence(String sentence) {
        return TextParserUtils.checkRegex(sentence, sentencePattern);
    }

    @Override
    public String toString() {
        return "Sentence{" + "sentence=" + super.originStr + '}';
    }

    public static void main(String[] args) {
        Sentence sentence = new Sentence("  It's a   very good day, isn't it?!!  ".trim());
        sentence.parse();
        System.out.println(sentence.parsedList);
        System.out.println(sentence.getWords());
    }

}
