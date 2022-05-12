//
// @lc app=leetcode.cn id=47 lang=java
//
// [47] permutations-ii
//
class Solution {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backward(res, new ArrayList<Integer>(), visited, nums);
        return res;
    }

    private static void backward(List<List<Integer>> res, ArrayList<Integer> path, boolean[] visited, int[] nums) {
        if (nums.length == path.size() && !res.contains(path)) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]))
                continue;
            path.add(nums[i]);
            visited[i] = true;
            backward(res, path, visited, nums);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}
// @lc code=end