package xsw.November21;
/*
319. 灯泡开关
初始时有 n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭一个。

第三轮，你每三个灯泡就切换一个灯泡的开关（即，打开变关闭，关闭变打开）。第 i 轮，你每 i 个灯泡就切换一个灯泡的开关。直到第 n 轮，你只需要切换最后一个灯泡的开关。

找出并返回 n 轮后有多少个亮着的灯泡。



示例 1：



输入：n = 3
输出：1
解释：
初始时, 灯泡状态 [关闭, 关闭, 关闭].
第一轮后, 灯泡状态 [开启, 开启, 开启].
第二轮后, 灯泡状态 [开启, 关闭, 开启].
第三轮后, 灯泡状态 [开启, 关闭, 关闭].

你应该返回 1，因为只有一个灯泡还亮着。
示例 2：

输入：n = 0
输出：0
示例 3：

输入：n = 1
输出：1


提示：

0 <= n <= 109
通过次数46,276提交次数81,376
 */

import org.junit.Test;

import java.util.ArrayList;

public class 灯泡开关_319 {

    public int bulbSwitch(int n) {
        int l = 0, r = 31622;
        while (l < r) {
            int m = (l + r) / 2;
            if (m * (m + 2) < n)
                l = m + 1;
            else
            r = m;
        }
        return l;
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(bulbSwitch(1000000000));
    }

    class Solution {
        // 问题最终转换为：在 [1,n] 中完全平方数的个数为多少。
        public int bulbSwitch(int n) {
            return (int)Math.sqrt(n);
        }
    }

//    作者：AC_OIer
//    链接：https://leetcode-cn.com/problems/bulb-switcher/solution/gong-shui-san-xie-jing-dian-shu-lun-tui-upnnb/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}