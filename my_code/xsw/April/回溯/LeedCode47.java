package xsw.April.回溯;

/*
47. 全排列 II
给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。



示例 1：

输入：nums = [1,1,2]
输出：
[[1,1,2],
 [1,2,1],
 [2,1,1]]
示例 2：

输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


提示：

1 <= nums.length <= 8
-10 <= nums[i] <= 10*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LeedCode47 {

    /*执行用时：742 ms, 在所有 Java 提交中击败了5.02%的用户*/
    public static List<List<Integer>> permuteUnique1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backward1(res, new ArrayList<Integer>(), visited, nums);
        return res;
    }

    private static void backward1(List<List<Integer>> res, ArrayList<Integer> path, boolean[] visited, int[] nums) {
        if (nums.length == path.size() && !res.contains(path)) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                path.add(nums[i]);
                visited[i] = true;
                backward1(res, path, visited, nums);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {1, 2, 1, 1, 2};
        System.out.println(permuteUnique(candidates));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
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
