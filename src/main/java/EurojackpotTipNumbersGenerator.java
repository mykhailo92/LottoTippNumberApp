import java.util.Set;

public class EurojackpotTipNumbersGenerator extends TipNumbersGenerator {
    protected static final int MIN_EUROJACKPOT_5_FROM_50 = 1;
    protected static final int MAX_EUROJACKPOT_5_FROM_50 = 50;
    private static final int MIN_EUROJACKPOT_2_FROM_10 = 1;
    private static final int MAX_EUROJACKPOT_2_FROM_10 = 10;
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
