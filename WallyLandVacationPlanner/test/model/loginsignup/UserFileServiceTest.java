package model.loginsignup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana
 */
public class UserFileServiceTest {
    
    private static final String TEMP_FILE_PATH = "users.txt";
    private UserFileService userFileService;
    private NewUser testUser;
    
    @Before
    public void setUp() {
        userFileService = new UserFileService();
        
        testUser = new NewUser("test@example.com", "password123");
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setAge(AgeEnum.adult);
        testUser.setPhoneNum("123-456-7890");
        userFileService.addUserToFile(testUser);
    }
    
    @After
    public void tearDown() {
        File file = new File(TEMP_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Test of addUserToFile method, of class UserFileService.
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testAddUserToFile() throws FileNotFoundException, IOException {
        userFileService.addUserToFile(testUser);

        // Verify the file contains the user data
        try (BufferedReader reader = new BufferedReader(new FileReader(TEMP_FILE_PATH))) {
            String line = reader.readLine();
            assertNotNull(line);
            String expectedData = "test@example.com,password123,John,Doe,adult,123-456-7890";
            assertEquals(expectedData, line);
        }
    }

    
    @Test
    public void testCheckCredentials_ValidCredentials() {
        userFileService.addUserToFile(testUser);

        // Test valid credentials
        assertTrue(userFileService.checkCredentials("test@example.com", "password123"));
    }

    @Test
    public void testCheckCredentials_InvalidCredentials() {
        userFileService.addUserToFile(testUser);

        // Test invalid credentials
        assertFalse(userFileService.checkCredentials("test@example.com", "wrongpassword"));
        assertFalse(userFileService.checkCredentials("wrongemail@example.com", "password123"));
    }
    
}
