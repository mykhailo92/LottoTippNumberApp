import java.io.FileWriter;
import java.io.IOException;

/**
 * Logger class for writing log messages to a file.
 */
public class Logger {

    public static final String LOG_FILE_PATH = "error.log";

    /**
     * Logs an error message to a log file.
     *
     * @param errorMessage The error message to log.
     */
    public static void logError(String errorMessage) {
        try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {
            writer.write(errorMessage + "\n");
        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben ins Logfile.");
        }
    }
}
