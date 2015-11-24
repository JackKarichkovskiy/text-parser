/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.localization;

/**
 * The interface that contains messages.
 *
 * @author Karichkovskiy Yevhen
 */
public interface Messages {

    String NULL_OBJECT_MESSAGE = "object == null";
    String NOT_DELIMITER_MESSAGE = "The symbol '%s' isn't a delimiter";
    String NOT_LETTER_MESSAGE = "The symbol isn't a letter";
    String NOT_VALID_STRING_MESSAGE = "Given text isn't validated by pattern";
    String IO_EXCEPTION_MESSAGE = "Some IO errors";
}
