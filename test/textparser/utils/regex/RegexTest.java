/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.utils.regex;

import java.util.regex.Pattern;
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
public class RegexTest {
    
    public RegexTest() {
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

//    /**
//     * Test of method, of class Regex.
//     */
//    @Test
//    public void testIsSpaceRegex() {
//        System.out.println("testIsSpaceRegex");
//        Regex regex = Regex.IS_SPACE_DELIMITER;
//        Pattern pattern = Pattern.compile(regex.getRegex());
//        String testStr = " ";
//        boolean result = pattern.matcher(testStr).matches();
//        boolean expResult = true;
//        assertEquals(expResult, result);
//    }
//    
//    /**
//     * Test of method, of class Regex.
//     */
//    @Test
//    public void testIsNotSpaceRegex() {
//        System.out.println("testIsNotSpaceRegex");
//        Regex regex = Regex.IS_SPACE_DELIMITER;
//        Pattern pattern = Pattern.compile(regex.getRegex());
//        String testStr = "s";
//        boolean result = pattern.matcher(testStr).matches();
//        boolean expResult = false;
//        assertEquals(expResult, result);
//    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsLetterRegex() {
        System.out.println("testIsLetterRegex");
        Regex regex = Regex.IS_LETTER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "Ц";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotLetterRegex() {
        System.out.println("testIsNotLetterRegex");
        Regex regex = Regex.IS_LETTER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = " ";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsWordDelimiterRegex() {
        System.out.println("testIsWordDelimiterRegex");
        Regex regex = Regex.IS_WORD_DELIMITER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "-";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotWordDelimiterRegex() {
        System.out.println("testIsNotWordDelimiterRegex");
        Regex regex = Regex.IS_WORD_DELIMITER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "s";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsSentenceDelimiterRegex() {
        System.out.println("testIsSentenceDelimiterRegex");
        Regex regex = Regex.IS_SENTENCE_DELIMITER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "-";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotSentenceDelimiterRegex() {
        System.out.println("testIsNotSentenceDelimiterRegex");
        Regex regex = Regex.IS_SENTENCE_DELIMITER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "'";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsSentenceEndDelimiterRegex() {
        System.out.println("testIsSentenceEndDelimiterRegex");
        Regex regex = Regex.IS_SENTENCE_END_DELIMITER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "!";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotSentenceEndDelimiterRegex() {
        System.out.println("testIsNotSentenceEndDelimiterRegex");
        Regex regex = Regex.IS_SENTENCE_END_DELIMITER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = ",";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsDelimiterRegex() {
        System.out.println("testIsDelimiterRegex");
        Regex regex = Regex.IS_TEXT_DELIMITER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "?";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotDelimiterRegex() {
        System.out.println("testIsNotDelimiterRegex");
        Regex regex = Regex.IS_TEXT_DELIMITER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "3";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsSimpleWordRegex() {
        System.out.println("testIsSimpleWordRegex");
        Regex regex = Regex.IS_SIMPLE_WORD;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "собака";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotSimpleWordRegex() {
        System.out.println("testIsNotSimpleWordRegex");
        Regex regex = Regex.IS_SIMPLE_WORD;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "dog-dog";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsWordWithExtensionRegex() {
        System.out.println("testIsWordWithExtensionRegex");
        Regex regex = Regex.IS_WORD_WITH_EXTENSION;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "file.config.xml";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotWordWithExtensionRegex() {
        System.out.println("testIsNotWordWithExtensionRegex");
        Regex regex = Regex.IS_WORD_WITH_EXTENSION;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "test.txt ";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsWordRegex() {
        System.out.println("testIsWordRegex");
        Regex regex = Regex.IS_WORD;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "isn't12";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotWordRegex() {
        System.out.println("testIsNotWordRegex");
        Regex regex = Regex.IS_WORD;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "isn't123,";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsWordWithCapitalRegex() {
        System.out.println("testIsWordWithCapitalRegex");
        Regex regex = Regex.IS_WORD_WITH_CAPITAL;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "Isn't";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotWordWithCapitalRegex() {
        System.out.println("testIsNotWordWithCapitalRegex");
        Regex regex = Regex.IS_WORD_WITH_CAPITAL;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "isn't123";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsSentenceRegex() {
        System.out.println("testIsSentenceRegex");
        Regex regex = Regex.IS_SENTENCE;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "Сочетание русского и английского текста,isn't it?!!";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotSentenceRegex() {
        System.out.println("testIsNotSentenceRegex");
        Regex regex = Regex.IS_SENTENCE;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "It's a very good idea, isn't it?!! ";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsParagraphRegex() {
        System.out.println("testIsParagraphRegex");
        Regex regex = Regex.IS_PARAGRAPH;
        Pattern pattern = Pattern.compile(regex.getRegex(), Pattern.UNIX_LINES);
        String testStr = "\tIt's a very   good idea, isn't it?!! Другое - sentence!! \n\n";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotParagraphRegex() {
        System.out.println("testIsNotParagraphRegex");
        Regex regex = Regex.IS_PARAGRAPH;
        Pattern pattern = Pattern.compile(regex.getRegex(), Pattern.UNIX_LINES);
        String testStr = "It's a very   good idea, isn't it?!! Some another - sentence!! \n";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotCodeRegex() {
        System.out.println("testIsNotCodeRegex");
        Regex regex = Regex.IS_CODE;
        Pattern pattern = Pattern.compile(regex.getRegex(), Pattern.UNIX_LINES);
        String testStr = "";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsExpressionDelimiterRegex() {
        System.out.println("testIsExpressionDelimiterRegex");
        Regex regex = Regex.IS_EXPRESSION_DELIMITER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = ";";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotExpressionDelimiterRegex() {
        System.out.println("testIsNotExpressionDelimiterRegex");
        Regex regex = Regex.IS_EXPRESSION_DELIMITER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "{";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsModifierRegex1() {
        System.out.println("testIsModifierRegex1");
        Regex regex = Regex.IS_MODIFIER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "protected";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotModifierRegex() {
        System.out.println("testIsNotModifierRegex");
        Regex regex = Regex.IS_MODIFIER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "class";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsCodeDelimiterRegex() {
        System.out.println("testIsCodeDelimiterRegex");
        Regex regex = Regex.IS_CODE_WORDS_DELIMITER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "\"";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotCodeDelimiterRegex() {
        System.out.println("testIsNotCodeDelimiterRegex");
        Regex regex = Regex.IS_CODE_WORDS_DELIMITER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = ";";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsIdentifierRegex() {
        System.out.println("testIsIdentifierRegex");
        Regex regex = Regex.IS_IDENTIFIER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "createUser";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotIdentifierRegex() {
        System.out.println("testIsNotIdentifierRegex");
        Regex regex = Regex.IS_IDENTIFIER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "create-user";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsFieldRegex() {
        System.out.println("testIsFieldRegex");
        Regex regex = Regex.IS_FIELD;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "public static final int a = new A(10, \"a\");";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotFieldRegex() {
        System.out.println("testIsNotFieldRegex");
        Regex regex = Regex.IS_FIELD;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "public static final int a = ;";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsExpression1Regex() {
        System.out.println("testIsExpression1Regex");
        Regex regex = Regex.IS_EXPRESSION;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "ArrayList a = new ArrayList();";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsExpression2Regex() {
        System.out.println("testIsExpression2Regex");
        Regex regex = Regex.IS_EXPRESSION;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "int b = 6,\n c = x + b;";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotExpressionRegex() {
        System.out.println("testIsNotExpressionRegex");
        Regex regex = Regex.IS_EXPRESSION;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "int a = new ArrayList(){\n}";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsInitializerRegex() {
        System.out.println("testIsInitializerRegex");
        Regex regex = Regex.IS_INITIALIZER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "static  {\nint x = 3; int b = 6,\n c = x + b;\n}";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotInitializerRegex() {
        System.out.println("testIsNotInitializerRegex");
        Regex regex = Regex.IS_INITIALIZER;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "static  {\nint x = 3; int b = 6,\n c = x + b;";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsMethodWithBodyRegex() {
        System.out.println("testIsMethodWithBodyRegex");
        Regex regex = Regex.IS_METHOD;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "public\n static int setAB(int a, \nint  b ) throws IOException, Exception {\n"
                + "this.a = a;\n"
                + "this.a = a;\n"
                + "clear();\n}";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsMethodWithoutBodyRegex() {
        System.out.println("testIsMethodWithoutBodyRegex");
        Regex regex = Regex.IS_METHOD;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "public\n static int setAB(int a, \nint  b ) throws IOException, Exception ;";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsNotMethodRegex() {
        System.out.println("testIsNotMethodRegex");
        Regex regex = Regex.IS_METHOD;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "public int setAB(int a \nint  b ) throws IOException, Exception ;";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsClassRegex() {
        System.out.println("testIsClassRegex");
        Regex regex = Regex.IS_CLASS;
        Pattern pattern = Pattern.compile(regex.getRegex());
        String testStr = "public class \n A extends  B implements C,  D {\n"
                + "private static double a = 10.0;\n"
                + "{b = 3;}\n"
                + "{b = 3;}\n"
                + "private int b;\n"
                + "public A(int b){\n"
                + "setB(b);"
                + "}\n"
                + "public void setB(int b){\n"
                + "this.b = b;\n"
                + "}\n"
                + "}";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of method, of class Regex.
     */
    @Test
    public void testIsCodeRegex() {
        System.out.println("testIsCodeRegex");
        Regex regex = Regex.IS_CODE;
        Pattern pattern = Pattern.compile(regex.getRegex(), Pattern.UNIX_LINES);
        String testStr = "public class B extends A{{int a = 0;}}\n" + "public class \n A extends  B implements C,  D {\n"
                + "private static double a = 10.0;\n"
                + "{b = 3;}\n"
                + "{b = 3;}\n"
                + "private int b;\n"
                + "public A(int b){\n"
                + "setB(b);"
                + "}\n"
                + "public void setB(int b){\n"
                + "this.b = b;\n"
                + "}\n"
                + "}\n";
        boolean result = pattern.matcher(testStr).matches();
        boolean expResult = true;
        assertEquals(expResult, result);
    }
}
