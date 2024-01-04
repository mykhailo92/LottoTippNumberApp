import java.util.Set;

public class LottoTipNumbersGenerator extends TipNumbersGenerator {
    private static final int MIN_LOTTO_6_FROM_49 = 1;
    private static final int MAX_LOTTO_6_FROM_49 = 49;
    @Override
    public int[] generateNumbers(Set<Integer> unluckyNumbers) {
        return generateRandomNumbers(MIN_LOTTO_6_FROM_49, MAX_LOTTO_6_FROM_49, 6, unluckyNumbers);
    }
}
