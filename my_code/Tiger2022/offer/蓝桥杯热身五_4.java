package Tiger2022.offer;

import java.util.Scanner;

/*
7-4 LQ_09 FJ的字符串 (20 分)
FJ在沙盘上写了这样一些字符串：

A1 = “A”

A2 = “ABA”

A3 = “ABACABA”

A4 = “ABACABADABACABA”

… …

你能找出其中的规律并写所有的数列AN吗？

输入格式:
仅有一个数：N ≤ 26。

输出格式:
请在这里描述输出格式。例如：对每一组输入，在一行中输出A+B的值。

输入样例:
请输出相应的字符串AN，以一个换行符结束。输出中不得含有多余的空格或换行、回车符。

3
输出样例:
在这里给出相应的输出。例如：

ABACABA
 */
public class 蓝桥杯热身五_4 {
    public static void main(String[] args) {
        char[] c = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        sb.append('A');
        for (int i = 1; i < n; i++) {
            String s = sb.toString();
            sb.append(c[i]).append(s);
        }
        System.out.print(sb.toString());
    }
}
