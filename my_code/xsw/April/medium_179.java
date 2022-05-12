package xsw.April;
/*
179. 最大数
给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。



示例 1：

输入：nums = [10,2]
输出："210"
示例 2：

输入：nums = [3,30,34,5,9]
输出："9534330"
示例 3：

输入：nums = [1]
输出："1"
示例 4：

输入：nums = [10]
输出："10"


提示：

1 <= nums.length <= 100
0 <= nums[i] <= 109
通过次数62,152提交次数160,670
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class medium_179 {

    public static String largestNumber1(int[] nums) {
        String[] res = new String[nums.length];
        int i = 0;
        for (int num : nums) {
            res[i++] = num + "";
        }

        Arrays.sort(res, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.charAt(0) == o2.charAt(0)) {
                    if (o1.length() == o2.length())
                        return Integer.parseInt(o1) - Integer.parseInt(o2);
                    else {
                        if (o1.length() < o2.length()) {
                            String t = o1;
                            o1 = o2;
                            o2 = t;
                        }
                        for (int i = 0; i < o2.length(); i++) {
                            if (o2.charAt(i) > o1.charAt(i))
                                return -1;
                            else if (o2.charAt(i) < o1.charAt(i))
                                return 1;
                        }
                        for (int i = 0; i < o2.length() && i < o1.length() - o2.length(); i++) {
                            char c = o1.charAt(i + o2.length());
                            if (o2.charAt(i) > c)
                                return -1;
                            else if (o2.charAt(i) < c)
                                return 1;
                        }
                        return o1.charAt(1) - o2.charAt(0);
                    }
                }
                return o1.charAt(0) - o2.charAt(0);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int j = res.length - 1; j >= 0; j--) {
            sb.append(res[j]);
        }
        return sb.toString();
    }

    /*执行用时：11 ms, 在所有 Java 提交中击败了22.62%的用户*/
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        int[] data = {0, 0};
        System.out.println(largestNumber(data));
    }

    public static String largestNumber(int[] nums) {
        String[] res = new String[nums.length];
        int i = 0;
        for (int num : nums) {
            res[i++] = num + "";
        }

        Arrays.sort(res, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String a = o1 + o2;
                String b = o2 + o1;
                int l = a.length();
                for (int j = 0; j < l; j++) {
                    if (a.charAt(j) > b.charAt(j))
                        return 1;
                    if (a.charAt(j) < b.charAt(j))
                        return -1;
                }
                return 0;
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int j = res.length - 1; j >= 0; j--) {
            sb.append(res[j]);
        }
        if(sb.charAt(0)=='0')
            return "0";
        return sb.toString();
    }

    public String largestNumber2(int[] nums) {
        int n = nums.length;
        // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });

        if (numsArr[0] == 0) {
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for (int num : numsArr) {
            ret.append(num);
        }
        return ret.toString();
    }
}