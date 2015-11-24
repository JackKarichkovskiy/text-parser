/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.parser;

import java.util.List;
import java.util.Queue;
import textparser.word.Word;

/**
 * The interface that represents the parsable entity.
 *
 * @author Karichkovskiy Yevhen
 */
public interface Parsable {

    /**
     * The method that parses the entity.
     */
    void parse();

    /**
     * The method composes parsed parts of entity.
     *
     * @return composed string of parts
     */
    String compose();

    /**
     * Method that returns all words in the entity.
     *
     * @return list of words
     */
    List<Word> getWords();

    /**
     * Method polls some ordered words from queue.
     *
     * @param queue - queue that contains ordered words.
     */
    void pollOrderedWords(Queue<Word> queue);
}
