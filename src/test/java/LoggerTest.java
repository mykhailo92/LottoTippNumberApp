import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class LoggerTest {

    /**
     * Test logging error messages using the Logger class.
     */
    @Test
    public void testLogger() {
        String errorMessage = "Test error message";
        Logger.logError(errorMessage);

        try {
            String content = new String(Files.readAllBytes(Paths.get(Logger.LOG_FILE_PATH)));
            assertTrue(content.contains(errorMessage));
        } catch (Exception e) {
            fail("Fehler beim Lesen der Log-Datei: " + e.getMessage());
        }
    }
}
