package xsw.April.个人赛;
/*
2. 乐团站位
通过的用户数9
尝试过的用户数27
用户总通过次数9
用户总提交次数37
题目难度Easy
某乐团的演出场地可视作 num * num 的二维矩阵 grid（左上角坐标为 [0,0])，每个位置站有一位成员。乐团共有 9 种乐器，乐器编号为 1~9，每位成员持有 1 个乐器。

为保证声乐混合效果，成员站位规则为：自 grid 左上角开始顺时针螺旋形向内循环以 1，2，...，9 循环重复排列。例如当 num = 5 时，站位如图所示

image.png

请返回位于场地坐标 [Xpos,Ypos] 的成员所持乐器编号。

示例 1：

输入：num = 3, Xpos = 0, Ypos = 2

输出：3

解释：
image.png

示例 2：

输入：num = 4, Xpos = 1, Ypos = 2

输出：5

解释：
image.png

提示：

1 <= num <= 10^9
0 <= Xpos, Ypos < num
 */

import java.util.Arrays;

public class easy_2 {

    public static int orchestraLayout1(int num, int xPos, int yPos) {
        int top = 0, right = num - 1;
        int left = 0, blow = num - 1;
        int index = 1;
        int x = 0, y = 0;
        while (true) {
            while (y <= right) {
                System.out.println(x + "---------->" + y);
                if (x != xPos) {
                    index += right - left;
                    y = right;
                    ++top;
                    break;
                } else {
                    if (y == yPos)
                        return index % 9 == 0 ? 9 : index % 9;
                    ++y;
                    ++index;
                }
            }
            while (x <= blow) {
                System.out.println(x + "---------->" + y);
                if (y != yPos) {
                    index += blow - top + 1;
                    x = blow;
                    --right;
                    break;
                } else {
                    if (x == xPos)
                        return index % 9 == 0 ? 9 : index % 9;
                    ++x;
                    ++index;
                }
            }
            while (y >= left) {
                System.out.println(x + "---------->" + y);
                if (x != xPos) {
                    index += right - left + 1;
                    y = left;
                    ++blow;
                    break;
                } else {
                    if (y == yPos)
                        return index % 9 == 0 ? 9 : index % 9;
                    --y;
                    ++index;
                }
            }
            while (x > top) {
                System.out.println(x + "---------->" + y);
                if (y != yPos) {
                    index += blow - top;
                    x = top;
                    ++left;
                    break;
                } else {
                    if (x == xPos)
                        return index % 9 == 0 ? 9 : index % 9;
                    --x;
                    ++index;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {2, 2, 1, 9};
        System.out.println(orchestraLayout(5, 3,3));
    }

    public static int orchestraLayout(int num, int xPos, int yPos) {
        int n = (num + 1) / 2;
        int x = Math.min(xPos + 1, num - xPos);
        int y = Math.min(yPos + 1, num - yPos);
        int i = Math.min(x, y);
        long start = (4L * (i - 1) * (num - 1) % 9 + 9 - 4L * (i - 1) * (i - 2) % 9) % 9;
        int cur = num - 1 - 2 * (i - 1);
        if (xPos + 1 == i) {
            return (int) ((start + yPos - (i - 1)) % 9 + 1);
        }
        if (num - yPos == i) {
            return (int) ((start + cur + xPos - (i - 1)) % 9 + 1);
        }
        if (num - xPos == i) {
            return (int) ((start + 2 * cur + (num - 1 - yPos) - (i - 1)) % 9 + 1);
        }
        return (int) ((start + 3 * cur + (num - 1 - xPos) - (i - 1)) % 9 + 1);
    }
}