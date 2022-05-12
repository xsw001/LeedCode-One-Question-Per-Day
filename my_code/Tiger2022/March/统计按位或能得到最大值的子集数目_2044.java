package Tiger2022.March;
/*

 */

import org.junit.Test;

public class 统计按位或能得到最大值的子集数目_2044 {

    @Test
    public void test() throws Exception {
        int[] data = {2, 2, 2};
        System.out.println(countMaxOrSubsets(data));
        System.out.println(5 | 5);
    }

    int count;
    int max;

    public int countMaxOrSubsets(int[] nums) {
        max = 0;
        count = 0;
        find(nums, 0, 0);
        return count;
    }

    private void find(int[] nums, int i, int sum) {
        if (sum > max) {
            max = sum;
            count = 1;
        } else if (sum == max)
            ++count;
        for (int j = i; j < nums.length; j++) {
            int back = sum;
            sum |= nums[j];
            find(nums, j + 1, sum);
            sum = back;
        }
    }

    private void find2(int[] nums, int i, int sum) {
        if (i == nums.length) {
            if (sum > max) {
                max = sum;
                count = 1;
            } else if (sum == max)
                ++count;
            return;
        }

        find2(nums, i + 1, sum | nums[i]);
        find2(nums, i + 1, sum);
    }

    public int countMaxOrSubset(int[] nums) {
        int mask = 1 << nums.length;
        int max = 0, count = 0;
        for (int i = 0; i < mask; i++) {
            int val = 0;
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1)
                    val |= nums[j];
            }
            if (val > max) {
                max = val;
                count = 1;
            } else if (val == max)
                ++count;
        }

        return count;
    }
}
