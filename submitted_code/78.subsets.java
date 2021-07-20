//
// @lc app=leetcode.cn id=78 lang=java
//
// [78] subsets
//
class Solution {
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
}
// @lc code=end