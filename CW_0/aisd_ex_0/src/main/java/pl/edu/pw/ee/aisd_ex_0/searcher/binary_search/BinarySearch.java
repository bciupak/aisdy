package pl.edu.pw.ee.aisd_ex_0.searcher.binary_search;

import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd_ex_0.searcher.service.Searching;

public class BinarySearch implements Searching {

    @Override
    public int search(int[] nums, int toFind) {
        int n = nums.length;

        int l = 0;
        int p = n;
        int mid;

        while(l <= p){
            mid = (int) Math.ceil(l + (p - l)/2);
            if(toFind > nums[mid])
                l = mid + 1;
   
            else if (toFind < nums[mid])
                p = mid-1;
            
            else
                return mid;
            
        }
        return -1;
    }

    private void validateInput(int[] nums) {
        if (isNull(nums)) {
            throw new IllegalArgumentException("Input nums array shound not be null!");
        }
    }

}
