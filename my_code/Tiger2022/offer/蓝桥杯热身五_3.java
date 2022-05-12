package Tiger2022.offer;

import java.util.Arrays;
import java.util.Scanner;

/*
7-3 LQ_13 矩形面积交 (20 分)
平面上有两个矩形，它们的边平行于直角坐标系的X轴或Y轴。对于每个矩形，我们给出它的一对相对顶点的坐标，请你编程算出两个矩形的交的面积。

输入格式:
输入仅包含两行，每行描述一个矩形。在每行中，给出矩形的一对相对顶点的坐标，每个点的坐标都用两个绝对值不超过10^7的实数表示。

输出格式:
输出仅包含一个实数，为交的面积，保留到小数后两位。

输入样例:
在这里给出一组输入。例如：

1 1 3 3
2 2 4 4
输出样例:
在这里给出相应的输出。例如：

1.00
 */
public class 蓝桥杯热身五_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double x1 = sc.nextDouble(), y1 = sc.nextDouble(), x2 = sc.nextDouble(),
                y2 = sc.nextDouble(), x3 = sc.nextDouble(), y3 = sc.nextDouble(),
                x4 = sc.nextDouble(), y4 = sc.nextDouble();
        double v = computeArea(x1, y1, x2, y2, x3, y3, x4, y4);
        System.out.println(String.format("%.2f", v));
    }

    public static double computeArea(double ax1, double ay1, double ax2, double ay2, double bx1, double by1, double bx2, double by2) {
        double overlapWidth = Math.min(ax2, bx2) - Math.max(ax1, bx1),
                overlapHeight = Math.min(ay2, by2) - Math.max(ay1, by1);
        return Math.max(overlapWidth, 0) * Math.max(overlapHeight, 0);
    }
}
