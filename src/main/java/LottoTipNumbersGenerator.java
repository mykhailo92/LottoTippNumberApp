import java.util.Set;

/**
 * Generates Lotto lottery tip series.
 */
public class LottoTipNumbersGenerator extends TipNumbersGenerator {
    /**
     * The minimum value for generating 6 numbers out of 49 in Lotto.
     */
    protected static final int MIN_LOTTO_6_FROM_49 = 1;

    /**
     * The maximum value for generating 6 numbers out of 49 in Lotto.
     */
    protected static final int MAX_LOTTO_6_FROM_49 = 49;

    /**
     * Generates an array of Lotto lottery numbers.
     *
     * @param unluckyNumbers Set of unlucky numbers to exclude.
     * @return An array of generated Lotto lottery numbers.
     */
    @Override
    public int[] generateNumbers(Set<Integer> unluckyNumbers) {
        return generateRandomNumbers(MIN_LOTTO_6_FROM_49, MAX_LOTTO_6_FROM_49, 6, unluckyNumbers);
    }
}
