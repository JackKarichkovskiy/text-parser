/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser;

import java.io.IOException;
import textparser.controller.TextParserCommand;
import textparser.io.ConsoleView;
import textparser.io.FileTextReader;
import textparser.io.View;

/**
 * Class initializes and starts the parser.
 *
 * @author Karichkovskiy Yevhen
 */
public class MainClass {

    public static void main(String[] args) throws IOException {
        View view = new ConsoleView();
        String filepath = "in/in.txt";
        FileTextReader reader = new FileTextReader(filepath);
        char sortCharacter = 'и';

        new TextParserCommand(view, reader, sortCharacter).execute();
    }
}
