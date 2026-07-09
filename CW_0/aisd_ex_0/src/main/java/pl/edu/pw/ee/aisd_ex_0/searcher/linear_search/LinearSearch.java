package pl.edu.pw.ee.aisd_ex_0.searcher.linear_search;

import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd_ex_0.searcher.service.Searching;

public class LinearSearch implements Searching {

    @Override
    public int search(int[] nums, int toFind) {
        validateInput(nums);
        
        int n = nums.length;
        int result = -1;
        
        for (int i = 0; i < n; i++) {
        if (nums[i] == toFind) {
            result = i;
        }
    }
        
        return result;
    }

    private void validateInput(int[] nums) {
        if (isNull(nums)) {
            throw new IllegalArgumentException("Input nums array shound not be null!");
        
        }
    }

}
