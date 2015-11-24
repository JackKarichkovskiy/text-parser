/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.symbol.letter;

import textparser.symbol.Symbol;
import textparser.symbol.SymbolCreator;

/**
 * Class produces the Letter objects.
 * @author Karichkovskiy Yevhen
 */
public class LetterCreator implements SymbolCreator {
    
    @Override
    public Symbol createSymbol(char character) {
        return new Letter(character);
    }
    
}
