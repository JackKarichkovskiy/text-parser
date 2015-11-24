/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.sentence;

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
 * The class that represents sentence in text.
 *
 * @author Karichkovskiy Yevhen
 */
public class Sentence implements Parsable {

    /**
     * Regex pattern for sentence validating.
     */
    private final Pattern sentencePattern = Regex.IS_SENTENCE.getPattern();
    
    /**
     * Regex pattern for word validating.
     */
    private final Pattern wordPattern = Regex.IS_WORD.getPattern();

    /**
     * The origin sentence string.
     */
    private final String sentence;

    /**
     * The list of words and delimiters in sentence.
     */
    private final List<Parsable> wordsAndDelimiters = new ArrayList<>();

    public Sentence(String sentence) {
        this.sentence = checkSentence(sentence);
    }

    @Override
    public void parse() {
        Matcher matcher = wordPattern.matcher(sentence);

        while (matcher.find()) {
            Word word = new Word(matcher.group().trim());
            word.parse();
            wordsAndDelimiters.add(word);

            char delimiter = ' ';
            for (int i = matcher.end(); i < sentence.length() && Delimiter.isDelimiter(delimiter = sentence.charAt(i)); i++) {
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

    private String checkSentence(String sentence) {
        return TextParserUtils.checkRegex(sentence, sentencePattern);
    }

    @Override
    public String toString() {
        return "Sentence{" + "sentence=" + sentence + '}';
    }

    public static void main(String[] args) {
        Sentence sentence = new Sentence("  It's a   very good day, isn't it?!!  ".trim());
        sentence.parse();
        System.out.println(sentence.wordsAndDelimiters);
        System.out.println(sentence.getWords());
    }

}
