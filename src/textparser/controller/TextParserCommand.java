/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.controller;

import java.io.IOException;
import textparser.io.FileTextReader;
import textparser.io.View;
import textparser.localization.Messages;
import textparser.parser.TextParser;
import textparser.utils.TextParserUtils;

/**
 * The command that parses the text and sorts by character. After that the
 * composed string of the text is returned to the View.
 *
 * @author Karichkovskiy Yevhen
 */
public class TextParserCommand implements Command {

    /**
     * The view in MVC pattern.
     */
    private View view;

    /**
     * IO service for reading text from file.
     */
    private FileTextReader reader;

    /**
     * Sorting character.
     */
    private char sortCharacter;

    public TextParserCommand(View view, FileTextReader reader, char sortCharacter) {
        this.view = TextParserUtils.checkNotNull(view);
        this.reader = TextParserUtils.checkNotNull(reader);
        this.sortCharacter = sortCharacter;
    }

    @Override
    public void execute() {
        String text;
        try {
            text = reader.readText();
        } catch (IOException ex) {
            view.outputMessage(Messages.IO_EXCEPTION_MESSAGE);
            return;
        }

        TextParser parser = new TextParser(text);
        parser.parse();

        new SortWordsByCharacterCommand(parser, sortCharacter).execute();

        view.outputMessage(parser.compose());
    }

}
