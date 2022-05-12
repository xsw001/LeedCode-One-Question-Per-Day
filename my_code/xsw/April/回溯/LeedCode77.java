package xsw.April.回溯;
/*
77. 组合
给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

示例:

输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
通过次数147,923提交次数193,083
 */

import java.util.ArrayList;
import java.util.List;

public class LeedCode77 {

    static List<List<Integer>> res = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();

    public static List<List<Integer>> combine(int n, int k) {
        dfs(n, k, 0);
        return res;
    }

    public static void dfs(int n, int k, int index) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < n; ++i) {
            path.add(i + 1);
            dfs(n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combine(6, 2).size());
    }


    //78题
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }
}