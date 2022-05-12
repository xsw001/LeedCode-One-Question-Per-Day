package xsw.Nov_Dec;/*
357. 计算各个位数不同的数字个数
给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。

示例:

输入: 2
输出: 91
解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
*/

import java.util.HashMap;

public class LeedCode357 {
    public static int countNumbersWithUniqueDigits(int n) {
        int all = (int) Math.pow(10, n);
        if (n < 2)
            return all;
        int res = 0;
        for (int i = 10; i < all; i++) {
            if (judge1(i)) {
                ++res;
            }
        }
        return res + 10;
    }

    public static boolean judge(int i) {
        HashMap<Integer, Integer> map = new HashMap<>();
        while (i != 0) {
            map.put(i % 10, map.getOrDefault(i % 10, 0) + 1);
            i /= 10;
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) > 1)
                return false;
        }
        return true;
    }

    public static boolean judge1(int ints) {
        String s = String.valueOf(ints);
        for (int i = 0; i <= 9; i++) {
            int start = s.indexOf(i + 48);
            int end = s.lastIndexOf(i + 48);
            if (end != start)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits1(10));
    }


    public static int countNumbersWithUniqueDigits1(int n) {
        if(n == 0) return 1;
        n = Math.min(n, 10);
        int ans = 10, base = 9, sum = 9;
        for(int i = 1; i < n; ++i){
            ans += sum *= base--;
        }
        return ans;
    }
}
