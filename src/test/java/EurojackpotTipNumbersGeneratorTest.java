import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test class for the EurojackpotTipNumbersGenerator class.
 */
public class EurojackpotTipNumbersGeneratorTest {

    /**
     * Test the generation of Eurojackpot tip numbers to ensure they are within the valid range.
     */
    @Test
    public void testGenerateTipSeriesEurojackpotNumbers() {
        Set<Integer> unluckyNumbers = new HashSet<>();
        EurojackpotTipNumbersGenerator eurojackpotGenerator = new EurojackpotTipNumbersGenerator();
        int[] eurojackpotNumbers = eurojackpotGenerator.generateNumbers(unluckyNumbers);

        assertEquals(7, eurojackpotNumbers.length);

        int[] euroNumbers5from50 = Arrays.copyOfRange(eurojackpotNumbers, 0, 5);
        int[] euroNumbers2from10 = Arrays.copyOfRange(eurojackpotNumbers, 5, 7);

        for (int number : euroNumbers5from50) {
            assertTrue(number >= EurojackpotTipNumbersGenerator.MIN_EUROJACKPOT_5_FROM_50 &&
                    number <= EurojackpotTipNumbersGenerator.MAX_EUROJACKPOT_5_FROM_50);
        }

        for (int number : euroNumbers2from10) {
            assertTrue(number >= EurojackpotTipNumbersGenerator.MIN_EUROJACKPOT_2_FROM_10 &&
                    number <= EurojackpotTipNumbersGenerator.MAX_EUROJACKPOT_2_FROM_10);
        }
    }

}
