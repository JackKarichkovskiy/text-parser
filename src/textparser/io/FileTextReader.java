/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textparser.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import textparser.utils.TextParserUtils;

/**
 * The class that reads text from the file.
 *
 * @author Karichkovskiy Yevhen
 */
public class FileTextReader {

    /**
     * Path to the file.
     */
    private String filepath;

    /**
     * Input file stream.
     */
    private FileReader fileReader;

    public FileTextReader(String filepath) throws FileNotFoundException {
        this.filepath = TextParserUtils.checkNotNull(filepath);
        this.fileReader = new FileReader(filepath);
    }

    /**
     * Reads text from file.
     *
     * @return the text from file.
     * @throws IOException - if some IO problems occured
     */
    public String readText() throws IOException {
        StringWriter sw = new StringWriter();

        try (BufferedReader bis = new BufferedReader(fileReader)) {
            String nextTextStr;
            while ((nextTextStr = bis.readLine()) != null) {
                sw.append(nextTextStr + '\n');
            }
        }
        return sw.toString();
    }

}
