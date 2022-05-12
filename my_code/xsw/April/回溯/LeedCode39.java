package xsw.April.回溯;

/*
* 39. 组合总和
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。
示例 1：

输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]
示例 2：

输入：candidates = [2,3,5], target = 8,
所求解集为：
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]


提示：

1 <= candidates.length <= 30
1 <= candidates[i] <= 200
candidate 中的每个元素都是独一无二的。
1 <= target <= 500
通过次数245,654提交次数339,643*/

import java.util.*;

public class LeedCode39 {

    public static List<List<Integer>> combinationSum1(int[] candidates, int target) {
        int begin = target;
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        int l = candidates.length;
        for (int i = 0; i < l; i++) {
            List<Integer> list = combination(candidates, i, target);
            if (list.isEmpty()) {
                target -= candidates[i];
                break;
            }
            int temp = 0;
            for (Integer num : list) {
                temp += num;
            }
            if (temp != begin)
                list.add(begin - temp);
            res.add(list);
            target -= candidates[i];
        }
        //backward(res, new ArrayList<Integer>(), begin, candidates, 0);
        return res;
    }

    private static List<Integer> combination(int[] candidates, int i, int target) {
        List<Integer> list = new ArrayList<>();
        if (candidates[i] > target)
            return list;
        while (i < candidates.length) {
            if (target % candidates[i] == 0) {
                for (int j = 0; j < target / candidates[i]; j++) {
                    list.add(candidates[i]);
                }
            }
            ++i;
        }

        return list;
    }

    public static void main(String[] args) {
        int[] candidates = {1,2,3};
        int target = 4;
        System.out.println(combinationSum(candidates, target));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backward(res, new ArrayList<Integer>(), target, candidates, 0);
        return res;
    }

    private static void backward(List<List<Integer>> res, ArrayList<Integer> path, int target, int[] candidates, int start) {
        if(0 > target)
            return;
        if (0 == target) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            backward(res, path, target-candidates[i], candidates, i);//子递归传了 i 而不是 i+1 ，因为元素可以重复选入集合，如果传 i+1 就不重复了。
            path.remove(path.size() - 1);
        }
    }
}
