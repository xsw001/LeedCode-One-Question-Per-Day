//
// @lc app=leetcode.cn id=40 lang=java
//
// [40] combination-sum-ii
//
class Solution {
    public static List<List<Integer>> combinationSum2(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backward(res, new ArrayList<Integer>(), target, nums,0);
        return res;
    }

    private static void backward(List<List<Integer>> res, ArrayList<Integer> path, int target, int[] nums,int start) {
        if(target == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if(i>start&&nums[i]==nums[i-1])
                continue;
            if(0 > target)
                return;
            path.add(nums[i]);
            backward(res,path, target-nums[i],nums,i+1);
            path.remove(path.size()-1);
        }
    }
}
// @lc code=end