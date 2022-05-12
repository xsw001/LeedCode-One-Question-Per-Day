package Tiger2022.offer;

import java.util.ArrayList;
import java.util.Scanner;

/*
输入正整数n, 计算S = 1!+2!+...+n!的末6位(不含前导0). 这里1<=n<=10
9
 .

输入样例:
例如输入：

20
输出样例:
输出：

820313
 */
public class 蓝桥杯热身五_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long n = in.nextInt();
        long sum = 0;
        long pre = 1;
        for (long i = 1; i <= n; i++) {
            long temp = String.valueOf(i).length() > 6 ? i % 1000000 : i;
            temp *= pre;
            temp = String.valueOf(temp).length() > 6 ? temp % 1000000 : temp;
            if(temp == 0)
                break;
            pre = temp;
            sum += temp;
            sum = String.valueOf(sum).length() > 6 ? sum % 1000000 : sum;
        }
        System.out.println(sum);
    }

}
