package xsw.November21;
/*
397. 整数替换
给定一个正整数 n ，你可以做如下操作：

如果 n 是偶数，则用 n / 2替换 n 。
如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
n 变为 1 所需的最小替换次数是多少？



示例 1：

输入：n = 8
输出：3
解释：8 -> 4 -> 2 -> 1
示例 2：

输入：n = 7
输出：4
解释：7 -> 8 -> 4 -> 2 -> 1
或 7 -> 6 -> 3 -> 2 -> 1
示例 3：

输入：n = 4
输出：2


提示：

1 <= n <= 231 - 1
通过次数20,429提交次数51,814
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class 整数替换_397 {

    public int integerReplacement(int n) {
        if (n == 1)
            return 0;
        if (n % 2 == 0)
            return 1 + integerReplacement(n / 2);
        return 2 + Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1));
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    public int integerReplacement1(int n) {
        if (n == 1)
            return 0;
        if (!map.containsKey(n)) {
            if (n % 2 == 0)
                map.put(n, integerReplacement(n / 2));
            else
                map.put(n,2 + Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1)));
        }
        return map.get(n);
    }

    class Solution {
        public int integerReplacement(int _n) {
            long n = _n;
            int ans = 0;
            while (n != 1) {
                if (n % 2 == 0) {
                    n >>= 1;
                } else {
                    //因为每个1都要被+或-处理1次，但0只需要右移即可，所以当右边低位变成2个0时
                    //计算的次数一定不会比无法变成2个0的多，只要不多，那就是最少。 只有3这个数字是特殊情况。
                    if (n != 3 && ((n >> 1) & 1) == 1) n++;
                    else n--;
                }
                ans++;
            }
            return ans;
        }
    }
//
//    作者：AC_OIer
//    链接：https://leetcode-cn.com/problems/integer-replacement/solution/gong-shui-san-xie-yi-ti-san-jie-dfsbfs-t-373h/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}