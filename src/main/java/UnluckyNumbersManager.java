import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UnluckyNumbersManager {

    private static final int MAX_UNLUCKY_NUMBERS = 6;
    private static final String UNLUCKY_NUMBERS_FILE_PATH = "unluckyNumbers.txt";

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

                        System.exit(1);
                    }

                    if (unluckyNumbers.size() >= MAX_UNLUCKY_NUMBERS) {
                        System.out.println("Maximale Anzahl von " + MAX_UNLUCKY_NUMBERS + " Unglückszahlen erreicht.");
                        break;
                    }
                    unluckyNumbers.add(number);

                } catch (NumberFormatException e) {
                    Logger.logError("Ungültige Zahleneingabe: " + e.getMessage());
                    System.exit(1);
                }
            }
        }
        saveUnluckyNumbersToFile(unluckyNumbers);
    }

    private static void saveUnluckyNumbersToFile(Set<Integer> unluckyNumbers) {
        try (FileWriter writer = new FileWriter(UNLUCKY_NUMBERS_FILE_PATH)) {
            for (int number : unluckyNumbers) {
                writer.write(number + "\n");
            }
            System.out.println("Ihre aktuelle Unglückszahlen: " + unluckyNumbers);
        } catch (IOException e) {
            Logger.logError("Fehler beim Speichern der Unglückszahlen in die Datei: " + e.getMessage());
        }
    }

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
