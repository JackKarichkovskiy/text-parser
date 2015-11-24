/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.word;

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
public class WordFactoryTest {
    
    public WordFactoryTest() {
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
     * Test of getWord method, of class WordFactory.
     */
    @Test
    public void testGetWord() {
        System.out.println("testGetWord");
        String word = "Java7-Java8";
        WordFactory instance = new WordFactory();
        Word expResult = new Word("Java7-Java8");
        Word result = instance.getWord(word);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getWord method, of class WordFactory.
     */
    @Test
    public void testSeveralWords() {
        System.out.println("testSeveralWords");
        String word = "Java7-Java8";
        WordFactory instance = new WordFactory();
        Word result1 = instance.getWord(word);
        Word result2 = instance.getWord(word);
        boolean result = result1 == result2;
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
}
