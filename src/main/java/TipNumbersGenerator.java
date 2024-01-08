import java.util.Scanner;
import java.util.Set;
import java.util.Random;
import java.util.Arrays;

/**
 * Abstract class for generating lottery tip series.
 */
public abstract class TipNumbersGenerator implements NumbersGenerator {

    /**
     * Generates an array of random numbers within a specified range, excluding unlucky numbers.
     *
     * @param min             The minimum value of the random numbers (inclusive).
     * @param max             The maximum value of the random numbers (inclusive).
     * @param count           The number of random numbers to generate.
     * @param unluckyNumbers  Set of unlucky numbers to exclude.
     * @return                An array of generated random numbers.
     */
    protected int[] generateRandomNumbers(int min, int max, int count, Set<Integer> unluckyNumbers) {
        Random random = new Random();
        int[] numbers = new int[count];

        for (int i = 0; i < count; i++) {
            int number;
            do {
                number = random.nextInt(max - min + 1) + min;
            } while (unluckyNumbers.contains(number));
            numbers[i] = number;
        }

        Arrays.sort(numbers);
        return numbers;
    }

    /**
     * Generates multiple tip series based on the selected game type.
     *
     * @param args            Command line arguments.
     * @param scanner         Scanner for user input.
     * @param unluckyNumbers  Set of unlucky numbers.
     */
    public static void generateTipSeries(String[] args, Scanner scanner, Set<Integer> unluckyNumbers) {
        NumbersGenerator numbersGenerator;

        try {
            String gameType = getGameType(args, scanner);

            if ("eurojackpot".equalsIgnoreCase(gameType)) {
                numbersGenerator = new EurojackpotTipNumbersGenerator();
            } else {
                numbersGenerator = new LottoTipNumbersGenerator();
            }

            boolean isGenerating = true;

            while (isGenerating) {
                for (int i = 0; i < 3; i++) {
                    int[] lotteryNumbers = numbersGenerator.generateNumbers(unluckyNumbers);

                    if (numbersGenerator instanceof EurojackpotTipNumbersGenerator) {
                        System.out.println("Eurojackpot Tippreihe " + (i + 1) + ": " +
                                Arrays.toString(Arrays.copyOfRange(lotteryNumbers, 0, 5)) +
                                " + " +
                                Arrays.toString(Arrays.copyOfRange(lotteryNumbers, 5, 7)));
                    } else {
                        System.out.println("Lotto Tippreihe " + (i + 1) + ": " +
                                Arrays.toString(lotteryNumbers));
                    }
                }

                String response;

                do {
                    System.out.print("Möchten Sie weitere Tippreihen generieren? (Ja/Nein): ");
                    response = scanner.nextLine();

                    if ("Nein".equalsIgnoreCase(response)) {
                        isGenerating = false;
                    } else if (!"Ja".equalsIgnoreCase(response)) {
                        System.out.println("Ungültige Eingabe. Bitte geben Sie 'Ja' oder 'Nein' ein.");
                    }
                } while (!"Ja".equalsIgnoreCase(response) && !"Nein".equalsIgnoreCase(response));
            }
        } catch (NumberFormatException e) {
            Logger.logError("Ungültige Zahleneingabe: " + e.getMessage());
        }
        scanner.close();
    }

    /**
     * Gets the game type (Lotto or Eurojackpot) from command line arguments or user input.
     *
     * @param args    Command line arguments.
     * @param scanner Scanner for user input.
     * @return        The selected game type.
     */
    private static String getGameType(String[] args, Scanner scanner) {
        if (args.length > 0) {
            return args[0].toLowerCase();
        } else {
            String gameType = "";

            while (!"lotto".equals(gameType) && !"eurojackpot".equals(gameType)) {
                System.out.print("Wählen Sie den Spieltyp (lotto/eurojackpot): ");
                gameType = scanner.nextLine().toLowerCase();

                if (gameType.isEmpty()) {
                    gameType = "lotto";
                }

                if (!"lotto".equals(gameType) && !"eurojackpot".equals(gameType)) {
                    String errorMessage = "Ungültiger Spieltyp: '" + gameType +
                            "'. Verwenden Sie 'lotto' oder 'eurojackpot'.";
                    Logger.logError(errorMessage);
                    System.out.println(errorMessage);
                }
            }

            return gameType;
        }
    }

}
