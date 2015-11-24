/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.io;

/**
 * The interface that represents View in MVC pattern.
 *
 * @author Karichkovskiy Yevhen
 */
public interface View {

    /**
     * Method outputs message to the view.
     *
     * @param message - message that needs to be outputted
     */
    void outputMessage(String message);

    /**
     * Method clears the View.
     */
    void clear();
}
