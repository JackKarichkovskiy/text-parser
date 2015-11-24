/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.word;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karichkovskiy Yevhen
 */
public class WordTest {
    
    public WordTest() {
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
     * Test of getWords method, of class Word.
     */
    @Test
    public void testGetWords() {
        System.out.println("getWords");
        Word instance = new Word("apple");
        List<Word> expResult = new ArrayList<>();
        expResult.add(new Word("apple"));
        List<Word> result = instance.getWords();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCharCountInWord method, of class Word.
     */
    @Test
    public void testGetCharCountInWord() {
        System.out.println("getCharCountInWord");
        char ch = 'r';
        Word instance = new Word("property");
        int expResult = 2;
        int result = instance.getCharCountInWord(ch);
        assertEquals(expResult, result);
    }

}
