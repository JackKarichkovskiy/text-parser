/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.io;

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
public class TextReaderTest {

    public TextReaderTest() {
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
     * Test of readText method, of class TextReader.
     */
    @Test
    public void testReadText1() throws Exception {
        System.out.println("testReadText1");
        String str
                = "\tThis is a text with some code. Another sentence.\n"
                + "\tSome Text!!!\n"
                + "\tSome code dsfj lkasd sd jlkdsa.sjdkafj l\n"
                + "\tText, again...\n"
                + "public class A{}\n";
        String filepath = "in/test/test1.txt";
        FileTextReader instance = new FileTextReader(filepath);
        String expResult = str;
        String result = instance.readText();
        assertEquals(expResult, result);
    }

    /**
     * Test of readText method, of class TextReader.
     */
    @Test
    public void testReadText2() throws Exception {
        System.out.println("testReadText2");
        String filepath = "in/test/test2.txt";
        FileTextReader instance = new FileTextReader(filepath);
        String expResult = "\tКакой-то русский текст!\n";
        String result = instance.readText();
        assertEquals(expResult, result);
    }

}
