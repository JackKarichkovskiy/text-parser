/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.symbol;

/**
 * The class that produces symbols.
 *
 * @author Karichkovskiy Yevhen
 */
public interface SymbolCreator {

    /**
     * Factory method for characters creation.
     * @param character - symbol that needs to be produced.
     * @return produced Symbol object
     */
    Symbol createSymbol(char character);
}
