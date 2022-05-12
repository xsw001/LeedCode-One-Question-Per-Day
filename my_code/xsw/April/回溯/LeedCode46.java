package xsw.April.回溯;

/*
46. 全排列
给定一个 没有重复 数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
通过次数306,955提交次数394,417*/

import xsw.Nov_Dec.TreeNode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeedCode46 {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backward(res, new ArrayList<Integer>(), visited, nums);
        return res;
    }

    private static void backward(List<List<Integer>> res, ArrayList<Integer> path, boolean[] visited, int[] nums) {
        if (nums.length == path.size()) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                path.add(nums[i]);
                visited[i] = true;
                backward(res, path, visited, nums);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6};
        System.out.println(permute(candidates));
    }
}
