/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.io;

import java.io.PrintStream;

/**
 * Class that represents console as a view in MVC pattern.
 *
 * @author Karichkovskiy Yevhen
 */
public class ConsoleView implements View {

    /**
     * The output stream to the console.
     */
    private PrintStream consoleOut = System.out;

    @Override
    public void outputMessage(String message) {
        consoleOut.println(message);
    }

    @Override
    public void clear() {
        consoleOut.close();
    }

}
