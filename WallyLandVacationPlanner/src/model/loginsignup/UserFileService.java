package model.loginsignup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserFileService {

    private static final String FILE_PATH = "users.txt";

    /**
     * Saves a NewUser object to a file by appending the user's data to the
     * specified file.
     *
     * @param user The NewUser instance to be saved.
     */
    public void addUserToFile(NewUser user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // Check if the file is empty, and avoid writing an unnecessary new line
            if (isFileEmpty()) {
                writer.write(formatUserData(user));
            } else {
                writer.newLine();  // Add a new line before writing the next user
                writer.write(formatUserData(user));
            }
            System.out.println("User saved to file successfully at: " + new java.io.File(FILE_PATH).getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving user to file: " + e.getMessage());
        }
    }

    /**
     * Formats the user data to a single line, separating fields by commas. This
     * makes it easier to parse when reading back the file.
     *
     * @param user The NewUser instance.
     * @return A formatted string with user data.
     */
    private String formatUserData(NewUser user) {
        return user.getEmail() + "," +
               user.getPassword() + "," +
               user.getFirstName() + "," +
               user.getLastName() + "," +
               user.getAge() + "," +
               user.getPhoneNum();
    }

    /**
     * Checks if the file is empty.
     *
     * @return true if the file is empty, false otherwise.
     */
    private boolean isFileEmpty() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            return reader.readLine() == null; // If the first line is null, the file is empty
        } catch (IOException e) {
            System.err.println("Error checking if file is empty: " + e.getMessage());
            return true;
        }
    }

    public boolean checkCredentials(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                // Ensure there are exactly 6 fields (email, password, firstName, lastName, age, phoneNum)
                if (credentials.length == 6) {
                    String storedEmail = credentials[0].trim();
                    String storedPassword = credentials[1].trim();
                    if (storedEmail.equals(email) && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading user file: " + e.getMessage());
        }
        return false;
    }
}
