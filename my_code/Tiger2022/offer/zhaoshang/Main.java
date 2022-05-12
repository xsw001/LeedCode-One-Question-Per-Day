package Tiger2022.offer.zhaoshang;

import java.util.HashMap;
import java.util.Scanner;

/*
链接：https://ac.nowcoder.com/acm/contest/33182/A
来源：牛客网

训练营需要给入营的 NN 位同学（1≤N≤10^5）（1≤N≤10
5
 ）拍合影留念，通过 1…N1…N 来对这 NN 位同学进行标识。最初，同学们按照 a_1,a_2,…,a_Na
1
​
 ,a
2
​
 ,…,a
N
​
 的顺序从左到右站成一排，工作人员则需要通过调整同学们的站位使同学们从左到右按照 b_1,b_2, …, b_Nb
1
​
 ,b
2
​
 ,…,b
N
​
  的顺序站成一排。每一次调整都将某位同学向左移动若干个位置。
请计算将同学们调整至目标顺序所需要的最少的调整次数。
输入描述:
输入的第一行包含N。第二行包含了a1,a2,…,aN。第三行包含了b1,b2, …, bN。

输出描述:
输出初始顺序调整至目标顺序所需要的最少调整次数。

示例1
输入
复制
5
1 2 3 4 5
1 2 3 4 5
输出
复制
0
示例2
输入
复制
5
5 1 3 2 4
4 5 2 1 3
输出
复制
2
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        HashMap<Integer, Integer> idx2val = new HashMap<>();
        HashMap<Integer, Integer> val2idx = new HashMap<>();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            idx2val.put(i + 1, a[i]);
            val2idx.put(a[i], i + 1);
        }
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            if (b[i] != idx2val.get(i + 1)) {
                ++count;
                int index = val2idx.get(b[i]);
                for (int j = index; j > i; --j) {
                    idx2val.put(j, idx2val.get(j - 1));
                    val2idx.put(idx2val.get(j), j);
                }
            }
        }
        System.out.println(count);
    }
}
