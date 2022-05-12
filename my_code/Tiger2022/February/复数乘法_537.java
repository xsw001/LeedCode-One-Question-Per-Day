package Tiger2022.February;
/*
537. 复数乘法
复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：

实部 是一个整数，取值范围是 [-100, 100]
虚部 也是一个整数，取值范围是 [-100, 100]
i2 == -1
给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。



示例 1：

输入：num1 = "1+1i", num2 = "1+1i"
输出："0+2i"
解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
示例 2：

输入：num1 = "1+-1i", num2 = "1+-1i"
输出："0+-2i"
解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。


提示：

num1 和 num2 都是有效的复数表示。
通过次数22,201提交次数29,876
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public  class 复数乘法_537 {

    public  String complexNumberMultiply(String num1, String num2) {
        String[] ab = num1.split("\\+");
        String[] cd = num2.split("\\+");
        int a = Integer.parseInt(ab[0]);
        int b = Integer.parseInt(ab[1].substring(0,ab[1].length()-1));
        int c = Integer.parseInt(cd[0]);
        int d = Integer.parseInt(cd[1].substring(0,cd[1].length()-1));
        return String.valueOf(a * c - b * d) + '+' + (a * d + c * b) + 'i';
    }

    @Test
    public void test() throws Exception {

        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        byte[] b = new byte[10 * 1024 * 1024];
        List<Integer> list = new ArrayList<>();
        System.out.println(complexNumberMultiply("13+-154i","2+31i"));
        System.gc();
        System.out.println("系统的最大空间 Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");    //系统的最大空间
        System.out.println("系统的空闲空间 free mem=" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");  //系统的空闲空间
        System.out.println("当前可用的总空间 total mem=" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");  //当前可用的总空间

    }

    class Solution {
        public String complexNumberMultiply(String num1, String num2) {
            String[] complex1 = num1.split("\\+|i");
            String[] complex2 = num2.split("\\+|i");
            int real1 = Integer.parseInt(complex1[0]);
            int imag1 = Integer.parseInt(complex1[1]);
            int real2 = Integer.parseInt(complex2[0]);
            int imag2 = Integer.parseInt(complex2[1]);
            return String.format("%d+%di", real1 * real2 - imag1 * imag2, real1 * imag2 + imag1 * real2);
        }
    }
}
