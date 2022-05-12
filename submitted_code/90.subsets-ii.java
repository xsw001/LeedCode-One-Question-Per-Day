//
// @lc app=leetcode.cn id=90 lang=java
//
// [90] subsets-ii
//
class Solution {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backward(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }

    private static void backward(List<List<Integer>> res, ArrayList<Integer> path, int[] nums, int start) {
        if (!res.contains(path))
            res.add(new ArrayList<Integer>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backward(res, path, nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
// @lc code=end