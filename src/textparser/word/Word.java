/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.regex.Pattern;
import textparser.parser.Parsable;
import textparser.symbol.Symbol;
import textparser.symbol.SymbolFactory;
import textparser.symbol.delimiter.Delimiter;
import textparser.symbol.delimiter.DelimiterCreator;
import textparser.symbol.letter.Letter;
import textparser.symbol.letter.LetterCreator;
import textparser.utils.TextParserUtils;
import textparser.utils.regex.Regex;

/**
 *
 * @author Karichkovskiy Yevhen
 */
public final class Word implements Parsable {

    /**
     * Default char for replacement of not validated characters.
     */
    private static final char DEFAULT_EMPTY_CHAR = 0x0000;

    /**
     * Regex pattern for word validating.
     */
    private final Pattern wordPattern = Pattern.compile(Regex.IS_WORD.getRegex());

    /**
     * String of origin word.
     */
    private final String word;

    /**
     * Array of letters of word.
     */
    private final Symbol[] letters;
    
    /**
     * Counts and caches the count of requested characters in word.
     */
    private final Map<Character, Integer> charCount = new HashMap<>();

    public Word(String word) {
        this.word = checkWord(word);
        this.letters = new Symbol[word.length()];
    }

    @Override
    public void parse() {
        SymbolFactory factory = new SymbolFactory();
        LetterCreator lCreator = new LetterCreator();
        DelimiterCreator dCreator = new DelimiterCreator();

        for (int i = 0; i < word.length(); i++) {
            char nextChar = word.charAt(i);

            if (Letter.isLetter(nextChar)) {
                letters[i] = factory.getSymbol(nextChar, lCreator);
            } else if (Delimiter.isDelimiter(nextChar)) {
                letters[i] = factory.getSymbol(nextChar, dCreator);
            } else {
                letters[i] = factory.getSymbol(DEFAULT_EMPTY_CHAR, lCreator); //If symbol isn't detected, set as DEFAULT
            }
        }
    }

    @Override
    public String compose() {
        StringBuilder strBuf = new StringBuilder();
        
        for(Symbol symbol : letters){
            strBuf.append(symbol.compose());
        }
        
        return strBuf.toString();
    }

    @Override
    public List<Word> getWords() {
        return new ArrayList<Word>(){
            {
                this.add(Word.this);
            }
        };
    }

    @Override
    public void pollOrderedWords(Queue<Word> queue) {}
    
    public int getCharCountInWord(char ch){
        if(charCount.containsKey(ch)){
            return charCount.get(ch);
        }
        
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == ch){
                count++;
            }
        }
        charCount.put(ch, count);
        return count;
    }

    public String getWord() {
        return word;
    }
    
    private String checkWord(String word) {
        return TextParserUtils.checkRegex(word, wordPattern);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.word);
        hash = 47 * hash + Arrays.deepHashCode(this.letters);
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
        final Word other = (Word) obj;
        if (!Objects.equals(this.word, other.word)) {
            return false;
        }
        if (!Arrays.deepEquals(this.letters, other.letters)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Word{" + "word=" + word + '}';
    }
    
    public static void main(String[] args) {
        Word word = new Word("Don't");
        word.parse();
        System.out.println(Arrays.toString(word.letters));
        Word word2 = new Word("D-on't");
        word2.parse();
        System.out.println(Arrays.toString(word2.letters));
    }
}
