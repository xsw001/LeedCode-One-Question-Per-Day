package xsw.March;
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
通过次数76,961提交次数123,355
 */

import java.util.*;

public class medium_90 {

    //执行结果：通过显示详情 执行用时：12 ms , 在所有 Java 提交中击败了6.72%的用户
    //内存消耗：38.6 MB, 在所有 Java 提交中击败了87.11%的用户
    public static List<List<Integer>> subsetsWithDup1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int top : nums) {

            int l = res.size();
            for (int i = 0; i < l; i++) {
                List<Integer> list = res.get(i);
                ArrayList<Integer> temp = new ArrayList<>(list);
                temp.add(top);
                Collections.sort(temp);
                if (!res.contains(temp))
                    res.add(temp);
            }

            ArrayList<Integer> list = new ArrayList<>();
            list.add(top);
            if (res.isEmpty() || !res.contains(list)) {
                res.add(list);
            }
        }
        res.add(new ArrayList<Integer>());
        return res;
    }

    public static void main(String[] args) {
        int[] data = {2, 1, 2};
        System.out.println(subsetsWithDup((data)));
    }

    public static List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        Arrays.sort(nums);
        List<Integer> tmp = new ArrayList<>();
        tmp.add(nums[0]);
        res.add(tmp);
        if (nums.length == 1) return res;
        int last = 0;
        for (int i = 0; i < nums.length; i++) {
            int top = nums[i];
            int l = res.size();
            if (i > 0 && nums[i] != nums[i - 1])
                last = l;
            for (int j = l - last; j < l; j++) {
                List<Integer> list = res.get(j);
                ArrayList<Integer> temp = new ArrayList<>(list);
                temp.add(top);
                res.add(temp);
            }
        }
        return res;
    }


    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> t = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            boolean flag = true;
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {//巧得不得鸟
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        flag = false;
                        break;
                    }
                    t.add(nums[i]);
                }
            }
            if (flag) {
                ans.add(new ArrayList<Integer>(t));
            }
        }
        return ans;
    }
}