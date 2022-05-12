package Tiger2022.offer;
/*
剑指 Offer 46. 把数字翻译成字符串
给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。



示例 1:

输入: 12258
输出: 5
解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"


提示：

0 <= num < 231
通过次数152,211提交次数287,891
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 把数字翻译成字符串_46 {

    @Test
    public void test() throws Exception {

        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};

        List<Integer> list = new ArrayList<>();
        System.out.println(translateNum(1492916348));

    }

    public int translateNum(int num) {
        if (num < 11)
            return 1;
        if (num < 26)
            return 2;
        String s = String.valueOf(num);
        int[] dp = new int[s.length()];
        dp[0] = 1;
        dp[1] = Integer.parseInt(s.substring(0, 2)) < 26 ? 2 : 1;
        for (int i = 2; i < s.length(); i++) {
            int n = Integer.parseInt(s.substring(i - 1, i + 1));
            if (n > 9 && n < 26)
                dp[i] = dp[i - 1] + dp[i - 2];
            else
                dp[i] = dp[i - 1];
        }
        return dp[s.length() - 1];
    }
    class Solution {
        public int translateNum(int num) {
            String s = String.valueOf(num); // 将数字转为字符串
            int n = s.length();
            int[] f = new int[n + 1];
            f[0] = 1;  //初始化
            for(int i = 1; i <= n; i++){
                f[i] = f[i - 1];  //单独翻译s[i]
                if(i > 1){
                    int t = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
                    if(t >= 10 && t <= 25) //组合的数字范围在10 ~ 25之间
                        f[i] += f[i - 2];  //将s[i] 和 s[i - 1]组合翻译
                }
            }
            return f[n];
        }
    }
}
