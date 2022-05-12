package Tiger2022.offer.zhaoshang;

import java.util.Scanner;

/*
链接：https://ac.nowcoder.com/acm/contest/33182/C
来源：牛客网

训练营举办了金融科技展厅的参观活动。展厅中有一台机器人，同学们决定通过机器人来进行游戏。游戏规则是，机器人所在的平面有一个坐标系，机器人的初始位置是(0, 0)，给出机器人的目的坐标(xg, yg)和N个给机器人的指令（1≤N≤40），其中第i个指令将使机器人向右（X轴正半轴）移动xg个单位，向上（Y轴正半轴）移动yg个单位（若xg 和yg 为负数，则为向左和向下移动，xg和yg均为整数。

从N条指令中选出K（1≤K≤N）条让机器人来执行，计算出最终能够让机器人到达(xg, yg)的方案数。

输入描述:
输入的第一行包含N。第二行包含了目的坐标(xg, yg)，其中(-109≤xg, yg≤109)。接下来的N行给出了指令，每行包含了两个整数xi和yi，其中(-109≤xi, yi≤109)，对于所有的xi、yi、xg和yg都不为0。

输出描述:
输出N行，每一行打印从N个指令中选取K个指令的情况下，能够让机器人到达目的坐标(xg, yg)的方案数。

示例1
输入
复制
7
5 10
-2 0
3 0
4 0
5 0
0 10
0 -10
0 10
输出
复制
0
2
0
3
0
1
0
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

    }
}
