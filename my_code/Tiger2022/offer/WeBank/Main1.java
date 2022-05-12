package Tiger2022.offer.WeBank;

/*
努力的人
时间限制： 3000MS
内存限制： 589824KB
题目描述：
       小亮来到了一个特殊的国度，这个国家的人有一个奇特的地方：如果一个人身边的人都比自己强，那么这个人会开始努力提升自己。

       现在有n个人排成一排，因为视线是有限的，所以每个人只能看见左边的x个人和右边的y个人。每个人都有一个能力值a_i,如果他视线能看到的人能力值都比他高，则他会开始努力提升自己。如果左边人数不足x个人，则左边的视线能看见左边所有人，如果右边并没有y个人，那么右边的视线仅仅覆盖右边的所有人。

       已知这n个人的编号从左到右为1~n，请问努力的人中最左边的人编号是多少。

       保证这n个人的能力值都不重复，且都在10^6以内。



输入描述
输入第一行包含三个空格隔开的正整数，n,x,y,含义如题。(1<=n<=10^5，1<=x,y<=10000)

第二行有n个正整数，表示n个人的能力值，中间用空格隔开。

输出描述
输出仅包含一个正整数，即努力的人中最左边的编号是多少。


样例输入
10 2 3
10 8 7 1 9 2 6 4 3 5
样例输出
4

提示
输入样例2

6 2 2

10 8 2 7 3 1

输出样例2

3



输入样例3

5 2 2

10 8 2 7 1

输出样例3

5


 */

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int j = i - 1;
            boolean f = false;
            while (j >= 0 && j - i <= x) {
                if (arr[j] <= arr[i]) {
                    f = true;
                    break;
                }
                --j;
            }
            if (f)
                continue;
            j = i + 1;
            while (j < n && j - i <= x) {
                if (arr[j] <= arr[i]) {
                    f = true;
                    break;
                }
                ++j;
            }
            if (f)
                continue;
            System.out.println(i + 1);
            return;
        }
    }
}
