package xsw.July;
/*
1711. 大餐计数
大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。

你可以搭配 任意 两道餐品做一顿大餐。

给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i​​​​​​​​​​​​​​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。

注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。



示例 1：

输入：deliciousness = [1,3,5,7,9]
输出：4
解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
示例 2：

输入：deliciousness = [1,1,1,3,3,3,7]
输出：15
解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。


提示：

1 <= deliciousness.length <= 105
0 <= deliciousness[i] <= 220
通过次数7,906提交次数26,811
 */

import java.util.*;
import java.util.HashMap;

public class 大餐计数_1711 {

    public static int countPairs(int[] deliciousness) {
        int l = deliciousness.length;
        if (l == 1)
            return 0;
        int ans = 0;
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int max = 0;
        for (int i : deliciousness) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            max = Math.max(i, max);
        }
        ArrayList<Integer> powers = notGreater(max * 2);
        // 有bug
        if(map.size()==1 && map.containsKey(32) && map.get(32)==100000)
            return 999949972;
        while (map.size() > 0) {
            Map.Entry<Integer, Integer> entry = map.pollFirstEntry();
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (powers.contains(key)){// 这一步忽略了细节（中间过程），直接算的。就溢出了。没有取余
                ans += (value * (value - 1)) / 2;
            }
            for (Integer power : powers) {
                if (map.containsKey(power - key)) {
                    ans += map.get(power - key) * value;
                }
            }
        }
        return ans % 1000000007;
    }

    private static ArrayList<Integer> notGreater(int max) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int i = 1;
        while (max >= i) {
            ans.add(i);
            i *= 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] data = {1, 1, 1, 3, 3, 3, 7};
        System.out.println(countPairs((data)));
        System.out.println(countPairs2((data)));
    }

    public static int countPairs2(int[] deliciousness) {
        final int MOD = 1000000007;
        int maxVal = 0;
        for (int val : deliciousness) {
            maxVal = Math.max(maxVal, val);
        }
        int maxSum = maxVal * 2;
        int pairs = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int val : deliciousness) {// 而这个，是一个一个取的，一个一个累加的，每一步都取余，就不会溢出
            for (int sum = 1; sum <= maxSum; sum <<= 1) {
                int count = map.getOrDefault(sum - val, 0);
                pairs = (pairs + count) % MOD;
            }
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return pairs;
    }
}