/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import textparser.utils.TextParserUtils;
import textparser.word.Word;

/**
 *
 * @author Karichkovskiy Yevhen
 */
public abstract class AbstractParsable implements Parsable {

    protected String originStr;

    protected final List<AbstractParsable> parsedList = new ArrayList<>();

    public AbstractParsable(String originStr) {
        this.originStr = TextParserUtils.checkNotNull(originStr);
    }

    @Override
    public String compose() {
        if (parsedList.isEmpty()) {
            return "";
        }

        StringBuilder strBuf = new StringBuilder();
        parsedList.stream().forEach((parseObj) -> {
            strBuf.append(parseObj.compose());
        });

        return strBuf.toString();
    }

    @Override
    public List<Word> getWords() {
        List<Word> result = new ArrayList<>();

        for (Parsable parsedPart : parsedList) {
            result.addAll(parsedPart.getWords());
        }

        return result;
    }

    @Override
    public void pollOrderedWords(Queue<Word> queue) {
        queue = TextParserUtils.checkNotNull(queue);

        for (Parsable sentence : parsedList) {
            sentence.pollOrderedWords(queue);
        }
    }

}
