/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.symbol;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import textparser.symbol.delimiter.Delimiter;
import textparser.symbol.delimiter.DelimiterCreator;
import textparser.symbol.letter.Letter;
import textparser.symbol.letter.LetterCreator;

/**
 *
 * @author Karichkovskiy Yevhen
 */
public class SymbolFactoryTest {
    
    public SymbolFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSymbol method, of class SymbolFactory.
     */
    @Test
    public void testGetLetterSymbol() {
        System.out.println("testGetLetterSymbol");
        char letter = 't';
        SymbolCreator creator = new LetterCreator();
        SymbolFactory instance = new SymbolFactory();
        Symbol expResult = new Letter('t');
        Symbol result = instance.getSymbol(letter, creator);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getSymbol method, of class SymbolFactory.
     */
    @Test
    public void testGetDelimiterSymbol() {
        System.out.println("testGetDelimiterSymbol");
        char letter = '-';
        SymbolCreator creator = new DelimiterCreator();
        SymbolFactory instance = new SymbolFactory();
        Symbol expResult = new Delimiter('-');
        Symbol result = instance.getSymbol(letter, creator);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getSymbol method, of class SymbolFactory.
     */
    @Test
    public void testGetSeveralSymbols() {
        System.out.println("testGetSeveralSymbols");
        char letter = '.';
        SymbolCreator creator = new DelimiterCreator();
        SymbolFactory instance = new SymbolFactory();
        Symbol result1 = instance.getSymbol(letter, creator);
        Symbol result2 = instance.getSymbol(letter, creator);
        boolean result = result1 == result2;
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
}
