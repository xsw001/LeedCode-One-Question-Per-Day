package xsw.July;
/*
1743. 从相邻元素对还原数组
存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。

给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。

题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。

返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。



示例 1：

输入：adjacentPairs = [[2,1],[3,4],[3,2]]
输出：[1,2,3,4]
解释：数组的所有相邻元素对都在 adjacentPairs 中。
特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
示例 2：

输入：adjacentPairs = [[4,-2],[1,4],[-3,1]]
输出：[-2,4,1,-3]
解释：数组中可能存在负数。
另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
示例 3：

输入：adjacentPairs = [[100000,-100000]]
输出：[100000,-100000]


提示：

nums.length == n
adjacentPairs.length == n - 1
adjacentPairs[i].length == 2
2 <= n <= 105
-105 <= nums[i], ui, vi <= 105
题目数据保证存在一些以 adjacentPairs 作为元素对的数组 nums
通过次数7,275提交次数11,335
 */

import java.util.*;

public class 从相邻元素对还原数组_1743 {

    public static int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        for (int[] adjacentPair : adjacentPairs) {
            add(map, adjacentPair[0], adjacentPair[1], visited);
            add(map, adjacentPair[1], adjacentPair[0], visited);
        }
//        System.out.println(visited);
//        System.out.println(map);
        Iterator<Integer> iterator = visited.iterator();
        int start = iterator.next();
        int[] ans = new int[n + 1];
        int i = 0;
        ans[i++] = start;
        ans[i++] = map.get(start).get(0);
        ans[n] = iterator.next();
        while (i < n) {
            ArrayList<Integer> re = map.get(ans[i - 1]);
            // 1 对 2
            if (re.get(0) == ans[i - 2]) {
                ans[i++] = re.get(1);
            } else {
                ans[i++] = re.get(0);
            }
        }
        return ans;
    }

    private static void add(HashMap<Integer, ArrayList<Integer>> map, int a, int b, HashSet<Integer> set) {
        if (set.contains(a))
            set.remove(a);
        else
            set.add(a);
        ArrayList<Integer> list = map.getOrDefault(a, new ArrayList<Integer>());
        list.add(b);
        map.put(a, list);
        //map.computeIfAbsent(a,k->new ArrayList<Integer>()).add(b);  // 相当于上边的三行代码
    }

    public static void main(String[] args) {
        int[][] data = {{4, -2}, {1, 4}, {-3, 1}};
        System.out.println(Arrays.toString(restoreArray(data)));
        System.out.println(Arrays.toString(Solution.restoreArray(data)));

    }


    public int[] restoreArray1(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int[] adjacentPair : adjacentPairs) {
            map.putIfAbsent(adjacentPair[0], new ArrayList<Integer>());
            map.putIfAbsent(adjacentPair[1], new ArrayList<Integer>());
            map.get(adjacentPair[0]).add(adjacentPair[1]);
            map.get(adjacentPair[1]).add(adjacentPair[0]);
        }

        int n = adjacentPairs.length + 1;
        int[] ret = new int[n];
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int e = entry.getKey();
            List<Integer> adj = entry.getValue();
            if (adj.size() == 1) {
                ret[0] = e;
                break;
            }
        }

        ret[1] = map.get(ret[0]).get(0);
        // 很帅，不用额外的visited记录遍历过得数，一个数字左右各牵一个，肯定有一个已经找到了，只需要向前看是谁就行了
        for (int i = 2; i < n; i++) {
            List<Integer> adj = map.get(ret[i - 1]);
            int num = ret[i - 2] == adj.get(0) ? adj.get(1) : adj.get(0); // 只需要向前看是谁就行了
            ret[i] = num;
        }
        return ret;
    }

/*
    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs/solution/cong-xiang-lin-yuan-su-dui-huan-yuan-shu-v55t/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    // 用时分布图中 简洁的代码  ----->   computeIfAbsent
    static class Solution {
        public static int[] restoreArray(int[][] adjacentPairs) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int[] a : adjacentPairs) {
                List<Integer> list = map.computeIfAbsent(a[0], k -> new ArrayList<>());
                list.add(a[1]);
                map.computeIfAbsent(a[1], k -> new ArrayList<>()).add(a[0]);
            }
            int[] res = new int[adjacentPairs.length + 1];
            map.forEach((key, value) -> {
                if (value.size() == 1 && res[0] == res[1]) {
                    res[0] = key;
                    res[1] = value.get(0);
                    for (int i = 2; i <= adjacentPairs.length; i++) {
                        int next = map.get(res[i - 1]).get(0);
                        res[i] = next != res[i - 2] ? next : map.get(res[i - 1]).get(1);
                    }
                }
            });
            return res;
        }
    }
}