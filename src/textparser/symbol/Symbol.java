/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import textparser.parser.Parsable;
import textparser.word.Word;

/**
 * Class that represents the symbol.
 * @author Karichkovskiy Yevhen
 */
public abstract class Symbol implements Parsable {

    /**
     * The origin symbol.
     */
    private final char character;

    public Symbol(char character) {
        this.character = checkSymbol(character);
    }

    @Override
    public void parse(){
        // for Adapter only
    }
    
    public String compose() {
        return "" + character;
    }

    @Override
    public List<Word> getWords() {
        return new ArrayList<>();
    }

    @Override
    public void pollOrderedWords(Queue<Word> queue) {}
    
    public char getCharacter() {
        return character;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.character;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Symbol other = (Symbol) obj;
        if (this.character != other.character) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Symbol{" + "character=" + character + '}';
    }

    /**
     * Method checks if symbol is validated.
     * @param character - input symbol
     * @return input character if validated
     * @throws IllegalArgumentException - if character isn't validated
     */
    protected abstract char checkSymbol(char character);
}
