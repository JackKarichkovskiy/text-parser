/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.symbol.delimiter;

import textparser.symbol.Symbol;
import textparser.symbol.SymbolCreator;

/**
 * Class that produces Delimiter objects.
 *
 * @author Karichkovskiy Yevhen
 */
public class DelimiterCreator implements SymbolCreator {

    @Override
    public Symbol createSymbol(char character) {
        return new Delimiter(character);
    }

}
