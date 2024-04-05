package dragon2024.march;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class m30_两数之和1 {

    @Test
    public void test() {
        int[] arr = {3, 2, 4};
        int[] twoSum = twoSum(arr, 6);
        System.out.println(Arrays.toString(twoSum));
    }

    public int[] twoSum(int[] nums, int target) {
        HashSet<Integer> set = new HashSet<>();
        int l = nums.length;
        for (int j : nums) {
            set.add(j);
        }
        for (int i = 0; i < l; ++i) {
            int num = target - nums[i];
            if (set.contains(num)) {
                for (int k = 0; k < l; k++) {
                    if (nums[k] == num && k != i)
                        return new int[]{i, k};
                }
            }
        }
        return new int[]{};
    }

    public int[] twoSum1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int l = nums.length;
        for (int i = 0; i < l; ++i) {
            int num = target - nums[i];
            if (map.containsKey(num)) {
                return new int[]{i, map.get(num)};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{};
    }
}
