package Tiger2022.offer.jinsahnyun;

import java.util.Scanner;

import java.util.HashMap;
import java.util.Scanner;

/*
魔法师
时间限制： 3000MS
内存限制： 589824KB
题目描述：
众所周知小Ka是一个魔术师，他可以将一个数字+1或者将这个数字变成他原来的2倍(3->4 或者 3->6)，但是他一天只能施展一次魔法。小Pa喜欢N这个数字,因此他想知道将1变成N最少需要几天。



输入描述
数据包含多组，第一行1个整数 T，表示数据组数。

接下来T行，每行包含1个整数N。

0<=N<=10^9 ， T 不超过50

输出描述
输出T行，每行对应输出最小需要的天数。


样例输入
2
2
3
样例输出
1
2

提示
样例输入2

5
6
12
18
24
30



样例输出2

3

4

5

5

7
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            long n = sc.nextInt();
            System.out.println(help(n));
        }
    }

    private static long help(long n) {
        if(n == 0)
            return -1;
        long ans = 0;
        while(n != 1){
            if(n % 2 == 0)
                n /= 2;
            else
                --n;
            ++ans;
        }
        return ans;
    }
}

