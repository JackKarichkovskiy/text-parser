/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.symbol;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that produces symbols.
 * @author Karichkovskiy Yevhen
 */
public class SymbolFactory {

    /**
     * Map for avoiding creation duplicates of symbols.
     */
    private final Map<Character, Symbol> symbols = new HashMap<>();

    /**
     * Method produces Symbol objects by input character.
     * @param letter - symbol that needs to be produced
     * @param creator - the class that creates the object of symbol
     * @return Symbol object
     */
    public Symbol getSymbol(char letter, SymbolCreator creator) {
        Symbol resultSymbol = symbols.get(letter);

        if (resultSymbol == null) {
            resultSymbol = creator.createSymbol(letter);
            symbols.put(letter, resultSymbol);
        }

        return resultSymbol;
    }
}
