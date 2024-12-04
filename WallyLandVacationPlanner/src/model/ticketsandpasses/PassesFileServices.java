package model.ticketsandpasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Ana
 */
public class PassesFileServices {

    private static final String FILE_PATH = "cart.txt";

    public void addPassesToFile() {

    }

//    private String formatPassData() {
//
//    }

    private boolean isFileEmpty() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            return reader.readLine() == null; // If the first line is null, the file is empty
        } catch (IOException e) {
            System.err.println("Error checking if file is empty: " + e.getMessage());
            return true;
        }
    }

}

