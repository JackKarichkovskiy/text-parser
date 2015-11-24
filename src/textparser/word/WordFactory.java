/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.word;

import java.util.HashMap;
import java.util.Map;
import textparser.utils.TextParserUtils;

/**
 * Realization of FlyWeight pattern for word storing.
 *
 * @author Karichkovskiy Yevhen
 */
public class WordFactory {

    /**
     * Storage of words.
     */
    private final Map<String, Word> words = new HashMap<>();

    /**
     * Method returns the object that represents input word.
     *
     * @param word - input string of word
     * @return object that represents word
     */
    public Word getWord(String word) {
        TextParserUtils.checkNotNull(word);

        Word resultWord = words.get(word);

        if (resultWord == null) {
            resultWord = new Word(word);
            words.put(word, resultWord);
        }

        return resultWord;
    }
}
