//
// @lc app=leetcode.cn id=216 lang=java
//
// [216] combination-sum-iii
//
class Solution {
    private static int K;

    public static List<List<Integer>> combinationSum3(int k, int n) {
        K = k;
        List<List<Integer>> res = new ArrayList<>();
        if (n > 45 || k * (1 + k) / 2 > n)
            return res;
        int l = n - (k - 1) * k / 2;
        if(l > 9)
            l = 9;
        int[] nums = new int[l];
        for (int i = 0; i < l; i++) {
            nums[i] = i + 1;
        }
        backward(res, new ArrayList<Integer>(), n, nums, 0);
        return res;
    }

    private static void backward(List<List<Integer>> res, ArrayList<Integer> path, int target, int[] candidates, int start) {
        if (0 > target)
            return;
        if (0 == target && path.size() == K) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            backward(res, path, target - candidates[i], candidates, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
// @lc code=end