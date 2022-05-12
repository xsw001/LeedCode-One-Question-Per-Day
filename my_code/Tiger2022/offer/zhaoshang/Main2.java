package Tiger2022.offer.zhaoshang;

import java.util.HashMap;
import java.util.Scanner;

/*
链接：https://ac.nowcoder.com/acm/contest/33182/B
来源：牛客网

训练营举行了“摆棋游戏”，同学们需要将N（1≤N≤105）个棋子逐个摆进棋盘，棋盘的空格内可摆放一枚棋子，在游戏开始时，棋盘是空的。已经被占用的空格(xi, yi)内不能再放入新的棋子，其中xi, yi 为整数且0≤xi, yi≤1000。游戏有一条规则是，不允许一枚棋子在水平、垂直方向恰好与另外3枚棋子相邻（每个棋子水平、垂直方向最多可以和上下左右共4个棋子相邻），如果有这种情况发生，则需要放入额外的棋子直到所有的棋子都符合游戏的规则（包括额外放入的棋子）。另外，额外新放入棋子的位置(x, y)不需要满足(0≤x, y≤1000)。对于每一个i（1≤i≤N），当第i个棋子放入的时候，请输出最少需要额外放入几颗棋子才能使棋子摆放符合游戏规则。

输入描述:
输入的第一行包含整数N。接下来的N行，每一行包含了两个空格隔开的整数，代表了棋子放入的位置（x, y）。

输出描述:
对于每一个i（1≤i≤N），输出能使棋子摆放符合游戏规则的最少棋子数。

示例1
输入
复制
9
0 1
1 0
1 1
1 2
2 1
2 2
3 1
3 2
4 1
输出
复制
0
0
0
1
0
0
1
2
4
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

    }
}
