import java.util.Set;
import java.util.Random;
import java.util.Arrays;

public abstract class TipNumbersGenerator implements NumbersGenerator {
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
}
