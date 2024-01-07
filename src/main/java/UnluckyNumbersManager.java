import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Manages the operations related to unlucky numbers.
 */
public class UnluckyNumbersManager {

    private static final int MAX_UNLUCKY_NUMBERS = 6;
    public static final String UNLUCKY_NUMBERS_FILE_PATH = "unluckyNumbers.txt";

    /**
     * Sets new unlucky numbers based on user input.
     *
     * @param unluckyNumbers Set of current unlucky numbers.
     * @param scanner        Scanner for user input.
     */
    public static void setNewUnluckyNumbers(Set<Integer> unluckyNumbers, Scanner scanner) {
        String response = "";

        while (!"Ja".equalsIgnoreCase(response) && !"Nein".equalsIgnoreCase(response)) {
            System.out.print("Möchten Sie neue Unglückszahlen festlegen? (Ja/Nein): ");
            response = scanner.nextLine();
        }

        if ("Ja".equalsIgnoreCase(response)) {
            System.out.print("Geben Sie neue Unglückszahlen (getrennt durch Leerzeichen) ein: ");
            String[] newUnluckyNumbersString = scanner.nextLine().split(" ");

            for (String num : newUnluckyNumbersString) {
                try {
                    int number = Integer.parseInt(num);
                    if (number < 1 || number > 50) {
                        String errorMessage = "Ungültige Unglückszahl: " + number + ". Gültiger Zahlenraum: " +
                                EurojackpotTipNumbersGenerator.MIN_EUROJACKPOT_5_FROM_50 + " bis " +
                                EurojackpotTipNumbersGenerator.MAX_EUROJACKPOT_5_FROM_50;
                        Logger.logError(errorMessage);
                        System.out.println(errorMessage);
                        break;
                    }

                    if (unluckyNumbers.size() >= MAX_UNLUCKY_NUMBERS) {
                        System.out.println("Maximale Anzahl von " + MAX_UNLUCKY_NUMBERS + " Unglückszahlen erreicht.");
                        break;
                    }
                    unluckyNumbers.add(number);

                } catch (NumberFormatException e) {
                    Logger.logError("Ungültige Zahleneingabe: " + e.getMessage());
                    break;
                }
            }
        }
        saveUnluckyNumbersToFile(unluckyNumbers);
    }

    /**
     * Deletes specified unlucky numbers based on user input.
     *
     * @param unluckyNumbers Set of current unlucky numbers.
     * @param scanner        Scanner for user input.
     */
    public static void deleteUnluckyNumbers(Set<Integer> unluckyNumbers, Scanner scanner) {
        String response = "";

        while (!"Ja".equalsIgnoreCase(response) && !"Nein".equalsIgnoreCase(response)) {
            System.out.print("Möchten Sie Unglückszahlen löschen? (Ja/Nein): ");
            response = scanner.nextLine();
        }

        if ("Ja".equalsIgnoreCase(response)) {
            System.out.print("Geben Sie die zu löschenden Unglückszahlen (getrennt durch Leerzeichen) ein: ");
            String[] deleteUnluckyNumbersString = scanner.nextLine().split(" ");

            for (String num : deleteUnluckyNumbersString) {
                try {
                    int number = Integer.parseInt(num);
                    unluckyNumbers.remove(number);
                } catch (NumberFormatException e) {
                    Logger.logError("Ungültige Zahleneingabe: " + e.getMessage());
                    System.exit(1);
                }
            }
            saveUnluckyNumbersToFile(unluckyNumbers);
        }
    }

    /**
     * Saves the set of unlucky numbers to a file.
     *
     * @param unluckyNumbers Set of unlucky numbers to save.
     */
    public static void saveUnluckyNumbersToFile(Set<Integer> unluckyNumbers) {
        List<Integer> sortedUnluckyNumbers = new ArrayList<>(unluckyNumbers);
        Collections.sort(sortedUnluckyNumbers);

        try (FileWriter writer = new FileWriter(UNLUCKY_NUMBERS_FILE_PATH)) {
            for (int number : sortedUnluckyNumbers) {
                writer.write(number + "\n");
            }
            System.out.println("Ihre aktuelle Unglückszahlen: " + sortedUnluckyNumbers);
        } catch (IOException e) {
            Logger.logError("Fehler beim Speichern der Unglückszahlen in die Datei: " + e.getMessage());
        }
    }

    /**
     * Reads a set of numbers from a file.
     *
     * @return A set of numbers read from the file.
     */
    public static Set<Integer> readNumbersFromFile() {
        Set<Integer> numbers = new HashSet<>();

        try {
            Scanner fileScanner = new Scanner(new java.io.File(UNLUCKY_NUMBERS_FILE_PATH));

            while (fileScanner.hasNextInt()) {
                numbers.add(fileScanner.nextInt());
            }
            fileScanner.close();
        } catch (IOException e) {
            Logger.logError("Fehler beim Lesen der Zahlen aus der Datei: " + e.getMessage());
        }
        return numbers;
    }

}
