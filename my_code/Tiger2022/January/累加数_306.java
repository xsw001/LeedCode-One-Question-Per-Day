package Tiger2022.January;
/*
306. 累加数
累加数 是一个字符串，组成它的数字可以形成累加序列。

一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。

给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。

说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。



示例 1：

输入："112358"
输出：true
解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
示例 2：

输入："199100199"
输出：true
解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199


提示：

1 <= num.length <= 35
num 仅由数字（0 - 9）组成


进阶：你计划如何处理由过大的整数输入导致的溢出?

通过次数21,760提交次数61,646
 */

import org.junit.Test;

import java.util.ArrayList;

public class 累加数_306 {
    // 溢出
    public boolean isAdditiveNumber1(String num) {
        int l = num.length();
        for (int i = 0; i < l / 2 + 1; i++) {
            int j = i + 1;
            for (int k = 1; k + j < l; k++) {
                int a = Integer.parseInt(num.substring(0, j));
                int b = Integer.parseInt(num.substring(j, j + k));
                if (k > l / 2 + 1)
                    break;
                StringBuilder sb = new StringBuilder();
                sb.append(a).append(b);
                while (sb.length() < l) {
                    sb.append(a + b);
                    if (sb.length() > l || !sb.toString().equals(num.substring(0, sb.length())))
                        break;
                    b = a + b;
                    a = b - a;
                }
                if (sb.toString().equals(num))
                    return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(isAdditiveNumber2("199001200"));
        System.out.println(sum("999123", "956789"));
    }

    public boolean isAdditiveNumber(String num) {
        int l = num.length();
        for (int i = 0; i < l / 2 + 1; i++) {
            int j = i + 1;
            for (int k = 1; k + j < l; k++) {
                String a = num.substring(0, j);
                String b = num.substring(j, j + k);
                if ((b.startsWith("0") && b.length() > 1) || k > l / 2 + 1)
                    break;
                StringBuilder sb = new StringBuilder();
                sb.append(a).append(b);
                while (sb.length() < l) {
                    String t = sum(a, b);
                    sb.append(t);
                    if (sb.length() > l || !sb.toString().equals(num.substring(0, sb.length())))
                        break;
                    a = b;
                    b = t;
                }
                if (sb.toString().equals(num))
                    return true;
            }
        }
        return false;
    }

    private String sum(String a, String b) {
        int carry = 0;
        if (a.length() > b.length())
            b = "0".repeat(a.length() - b.length()) + b;
        else if (b.length() > a.length())
            a = "0".repeat(b.length() - a.length()) + a;
        int l = a.length();
        StringBuilder builder = new StringBuilder();
        for (int i = l - 1; i >= 0; i--) {
            int c = a.charAt(i) - '0' + b.charAt(i) - '0' + carry;
            builder.append(c % 10);
            carry = c / 10;
        }
        if (carry == 1)
            builder.append(1);
        return builder.reverse().toString();
    }


    public boolean isAdditiveNumber2(String num) {
        return dfs(num, 0, 0, 0, 0);
    }

    private boolean dfs(String num, int index, int count, long prevprev, long prev) {
        if (index >= num.length()) {
            return count > 2;
        }

        long current = 0;
        for (int i = index; i < num.length(); i++) {
            char c = num.charAt(i);

            if (num.charAt(index) == '0' && i > index) {
                // 剪枝1：不能做为前导0，但是它自己是可以单独做为0来使用的
                return false;
            }

            current = current * 10 + c - '0';

            if (count >= 2) {
                long sum = prevprev + prev;
                if (current > sum) {
                    // 剪枝2：如果当前数比之前两数的和大了，说明不合适
                    return false;
                }
                if (current < sum) {
                    // 剪枝3：如果当前数比之前两数的和小了，说明还不够，可以继续添加新的字符进来
                    continue;
                }
            }

            // 当前满足条件了，或者还不到两个数，向下一层探索
            if (dfs(num, i + 1, count + 1, prev, current)) {
                return true;
            }
        }

        return false;
    }

/*
    作者：tong-zhu
    链接：https://leetcode-cn.com/problems/additive-number/solution/tong-ge-lai-shua-ti-la-dfs-jian-zhi-by-t-jxsf/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}