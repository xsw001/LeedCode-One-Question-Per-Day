package xsw.May.第241场周赛;
/*

 */

import java.util.ArrayList;
import java.util.List;

public class easy_5759 {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backward(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }

    private static void backward(List<List<Integer>> res, ArrayList<Integer> path, int[] nums, int start) {
        res.add(new ArrayList<Integer>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backward(res, path, nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static int subsetXORSum(int[] nums) {
        if (nums.length == 0)
            return 0;
        List<List<Integer>> subsets = subsets(nums);
        int res = 0;
        for (List<Integer> subset : subsets) {
            if (subset.size() > 0) {
                int t = 0;
                for (Integer i : subset) {
                    t ^= i;
                }
                res += t;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(subsetXORSum2(data));
    }


    public static int subsetXORSum2(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int mask = 0; mask < (1 << n); ++mask) {
            int temp = 0;
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {//枚举 mask 的所有位数
                    temp ^= nums[i];
                }
            }
            res += temp;
        }
        return res;
    }
}