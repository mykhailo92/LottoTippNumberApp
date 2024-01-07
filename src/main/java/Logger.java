import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private static final String LOG_FILE_PATH = "error.log";

    public static void logError(String errorMessage) {
        try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {
            writer.write(errorMessage + "\n");
        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben ins Logfile.");
        }
    }
}
