package xsw.April.回溯;

/*
90. 子集 II
给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。



示例 1：

输入：nums = [1,2,2]
输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
示例 2：

输入：nums = [0]
输出：[[],[0]]


提示：

1 <= nums.length <= 10
-10 <= nums[i] <= 10
通过次数107,033提交次数169,022

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeedCode90 {

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

    public static void main(String[] args) {
        int[] candidates = {1, 4, 4, 4, 4};
        System.out.println(subsetsWithDup(candidates));
    }
}
