package xsw.April.回溯;
/*
216. 组合总和 III
找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

说明：

所有数字都是正整数。
解集不能包含重复的组合。
示例 1:

输入: k = 3, n = 7
输出: [[1,2,4]]
示例 2:

输入: k = 3, n = 9
输出: [[1,2,6], [1,3,5], [2,3,4]]
通过次数75,023提交次数101,396
 */

import java.util.*;

public class LeedCode216 {
    private static int K;

    /*执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户*/
    public static List<List<Integer>> combinationSum3(int k, int n) {
        K = k;
        List<List<Integer>> res = new ArrayList<>();
/*        if (k * (1 + k) / 2 > n)
            return res;    执行用时：1 ms, 在所有 Java 提交中击败了50.80%的用户 */
        if (n > 45 || k * (1 + k) / 2 > n)
            return res;
        int l = n - (k - 1) * k / 2;
        if (l > 9)
            l = 9;
        boolean[] visited = new boolean[l+1];
        backward(res, new ArrayList<Integer>(), visited, n, l,0);
        return res;
    }

    private static void backward(List<List<Integer>> res, ArrayList<Integer> path, boolean[] visited, int target, int l,int start) {
        if (0 == target && path.size() == K) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        if (target < 0)
            return;
        if (path.size() > K)
            return;
        for (int i = start; i <= l; i++) {
            if (visited[i])
                continue;
            path.add(i + 1);
            visited[i] = true;
            backward(res, path, visited, target - i - 1, l,i+1);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum3(2, 6));
    }
/*    private static int K;

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
    }*/
}