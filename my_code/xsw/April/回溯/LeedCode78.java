package xsw.April.回溯;

/*
78. 子集
给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。



示例 1：

输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
示例 2：

输入：nums = [0]
输出：[[],[0]]


提示：

1 <= nums.length <= 10
-10 <= nums[i] <= 10
nums 中的所有元素 互不相同
通过次数238,253提交次数298,590
*/

import java.util.ArrayList;
import java.util.List;

public class LeedCode78 {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backward(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }

    private static void backward(List<List<Integer>> res, ArrayList<Integer> path, int[] nums, int start) {
        res.add(new ArrayList<Integer>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backward(res, path, nums, i+1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6};
        System.out.println(subsets(candidates));
    }
}
