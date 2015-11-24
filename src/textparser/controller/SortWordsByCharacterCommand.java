/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.controller;

import textparser.parser.TextParser;

/**
 * Command that sorts all words by character.
 *
 * @author Karichkovskiy Yevhen
 */
public class SortWordsByCharacterCommand implements Command {

    /**
     * The character that decides the sort ordering.
     */
    private char character;

    /**
     * The parser of text and contains the realization of sorting by character.
     */
    private TextParser parser;

    public SortWordsByCharacterCommand(TextParser parser, char character) {
        this.parser = parser;
        this.character = character;
    }

    @Override
    public void execute() {
        parser.sortByCharCountInWord(character);
    }

}
