
package pl.edu.pw.ee.aisd2025zex4.performance;
import java.util.Random;

public class DataGenerator{
    public static String[] generateRandData(int size) {
        assert size >= 0;

        String[] nums = new String[size];

        long eliteSeed = 31337;
        Random rand = new Random(eliteSeed);

        for (int i = 0; i < size; i++) {
            nums[i] = Double.toString(rand.nextDouble());
        }

        return nums;
    }

    public static String[] generateAscData(int size) {
        assert size >= 0;

        String[] nums = new String[size];
        double start = 100_000_000;

        for (int i = 0; i < size; i++) {
            nums[i] = Double.toString(start + i);
        }

        return nums;
    }

    public static String[] generateDescData(int size) {
        assert size >= 0;

        String[] nums = new String[size];
        double start = 100_000_000;

        for (int i = 0; i < size; i++) {
            nums[i] = Double.toString(start + (size - 1 - i));
        }

        return nums;
    }
}
