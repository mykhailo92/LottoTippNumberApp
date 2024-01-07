import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for the LottoTipNumbersGenerator class.
 */
public class LottoTipNumbersGeneratorTest {

    /**
     * Test the generation of Lotto tip numbers to ensure they are within the valid range.
     */
    @Test
    public void testGenerateTipSeriesLottoNumbers() {
        Set<Integer> unluckyNumbers = new HashSet<>();
        LottoTipNumbersGenerator lottoGenerator = new LottoTipNumbersGenerator();
        int[] lottoNumbers = lottoGenerator.generateNumbers(unluckyNumbers);

        assertEquals(6, lottoNumbers.length);

        for (int number : lottoNumbers) {
            assertTrue(number >= LottoTipNumbersGenerator.MIN_LOTTO_6_FROM_49 &&
                    number <= LottoTipNumbersGenerator.MAX_LOTTO_6_FROM_49);
        }
    }

}
