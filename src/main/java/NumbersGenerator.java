import java.util.Set;

/**
 * Interface for generating lottery tips.
 */
public interface NumbersGenerator {

    /**
     * Generates an array of numbers for a lottery tip, excluding specified unlucky numbers.
     *
     * @param unluckyNumbers Set of unlucky numbers to exclude.
     * @return An array of generated lottery numbers.
     */
    int[] generateNumbers(Set<Integer> unluckyNumbers);
}
