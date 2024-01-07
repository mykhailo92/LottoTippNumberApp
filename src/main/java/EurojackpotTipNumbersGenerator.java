import java.util.Set;

/**
 * Generates Eurojackpot lottery tip series.
 */
public class EurojackpotTipNumbersGenerator extends TipNumbersGenerator {
    /**
     * The minimum value for generating 5 numbers out of 50 in Eurojackpot.
     */
    protected static final int MIN_EUROJACKPOT_5_FROM_50 = 1;

    /**
     * The maximum value for generating 5 numbers out of 50 in Eurojackpot.
     */
    protected static final int MAX_EUROJACKPOT_5_FROM_50 = 50;

    /**
     * The minimum value for generating 2 numbers out of 10 in Eurojackpot.
     */
    protected static final int MIN_EUROJACKPOT_2_FROM_10 = 1;

    /**
     * The maximum value for generating 2 numbers out of 10 in Eurojackpot.
     */
    protected static final int MAX_EUROJACKPOT_2_FROM_10 = 10;

    /**
     * Generates an array of Eurojackpot lottery numbers.
     *
     * @param unluckyNumbers Set of unlucky numbers to exclude.
     * @return An array of generated Eurojackpot lottery numbers.
     */
    @Override
    public int[] generateNumbers(Set<Integer> unluckyNumbers) {
        int[] euroNumbers5from50 = generateRandomNumbers(MIN_EUROJACKPOT_5_FROM_50, MAX_EUROJACKPOT_5_FROM_50,
                5, unluckyNumbers);
        int[] euroNumbers2from10 = generateRandomNumbers(MIN_EUROJACKPOT_2_FROM_10, MAX_EUROJACKPOT_2_FROM_10,
                2, unluckyNumbers);

        int[] result = new int[euroNumbers5from50.length + euroNumbers2from10.length];
        System.arraycopy(euroNumbers5from50, 0, result, 0, euroNumbers5from50.length);
        System.arraycopy(euroNumbers2from10, 0, result, euroNumbers5from50.length, euroNumbers2from10.length);

        return result;
    }
}
