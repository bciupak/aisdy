package pl.edu.pw.ee.aisd2025zex2.performance;

import pl.edu.pw.ee.aisd2025zex2.HashListChainingMultiplicativeHashing;
import static pl.edu.pw.ee.aisd2025zex2.performance.utils.HashSizeGenerator.generateHashSizePrimeNums;

public class PerformanceHashListChainingMultiplicativeHashingSizePrimeNumTest extends PerformanceTest {

    public PerformanceHashListChainingMultiplicativeHashingSizePrimeNumTest() {
        super(HashListChainingMultiplicativeHashing.class);
    }

    @Override
    int[] getAllHashSizes() {
        return generateHashSizePrimeNums(N_VARIANTS);
    }
}
