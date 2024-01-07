import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UnluckyNumbersManagerTest {

    /**
     * Test setting new unlucky numbers through user input.
     */
    @Test
    public void testSetNewUnluckyNumbers() {
        String userInput = "Ja\n3 7 15\nNein\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Set<Integer> unluckyNumbers = new HashSet<>();
        UnluckyNumbersManager.setNewUnluckyNumbers(unluckyNumbers, scanner);

        assertTrue(unluckyNumbers.contains(3));
        assertTrue(unluckyNumbers.contains(7));
        assertTrue(unluckyNumbers.contains(15));

        scanner.close();
    }

    /**
     * Test deleting specified unlucky numbers through user input.
     */
    @Test
    public void testDeleteUnluckyNumbers() {
        Set<Integer> unluckyNumbers = new HashSet<>();
        unluckyNumbers.add(5);
        unluckyNumbers.add(10);
        unluckyNumbers.add(20);

        // Set up user input for deletion
        String userInput = "Ja\n10 20\nNein\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        UnluckyNumbersManager.deleteUnluckyNumbers(unluckyNumbers, scanner);

        assertFalse(unluckyNumbers.contains(10));
        assertFalse(unluckyNumbers.contains(20));

        scanner.close();
    }

    /**
     * Test saving and reading numbers to/from a file using UnluckyNumbersManager.
     */
    @Test
    public void testSaveAndReadNumbersToFile() {
        Set<Integer> unluckyNumbers = new HashSet<>();
        unluckyNumbers.add(5);
        unluckyNumbers.add(10);

        UnluckyNumbersManager.saveUnluckyNumbersToFile(unluckyNumbers);

        Set<Integer> readNumbers = UnluckyNumbersManager.readNumbersFromFile();
        assertEquals(unluckyNumbers, readNumbers);
    }
}
