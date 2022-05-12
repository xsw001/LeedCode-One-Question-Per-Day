package xsw.November21;
/*
391. 完美矩形
给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。
这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。

如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。


示例 1：


输入：rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
输出：true
解释：5 个矩形一起可以精确地覆盖一个矩形区域。
示例 2：


输入：rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
输出：false
解释：两个矩形之间有间隔，无法覆盖成一个矩形。
示例 3：


输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[3,2,4,4]]
输出：false
解释：图形顶端留有空缺，无法覆盖成一个矩形。
示例 4：


输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
输出：false
解释：因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。


提示：

1 <= rectangles.length <= 2 * 104
rectangles[i].length == 4
-105 <= xi, yi, ai, bi <= 105
通过次数6,252提交次数15,813
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class 完美矩形_391 {

    // set 没法判断 數組内部 是否相等
    public boolean isRectangleCover1(int[][] rectangles) {
        HashSet<int[]> set = new HashSet<>();
        int area = 0;
        int[][] arr = new int[4][2];
        for (int[] rectangle : rectangles) {
            arr[0] = new int[]{rectangle[0], rectangle[1]};
            arr[1] = new int[]{rectangle[0], rectangle[3]};
            arr[2] = new int[]{rectangle[2], rectangle[3]};
            arr[3] = new int[]{rectangle[2], rectangle[1]};
            area += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
            for (int i = 0; i < 4; i++) {
                if (set.contains(arr[i]))
                    set.remove(arr[i]);
                else
                    set.add(arr[i]);
            }
            arr = new int[4][2];
        }
        if (set.size() != 4)
            return false;
        int i = 0;
        for (int[] ints : set) arr[i++] = ints;
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0])
                return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        return area == (arr[3][0] - arr[1][0]) * (arr[2][1] - arr[0][1]);
    }

    @Test
    public void test() {
        int[][] data = {{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(isRectangleCover(data));
    }

    //把每个子矩形的面积累加，四个坐标放进一个vector，然后sort一下，相同的坐标消去。
    //最后剩下4个出现奇数次的点，且这个四个点围成的矩形面积等于子矩形面积和，则为true
    public boolean isRectangleCover(int[][] rectangles) {
        HashSet<String> set = new HashSet<>();
        int area = 0;
        String[] arr = new String[4];
        for (int[] rectangle : rectangles) {
            arr[0] = rectangle[0] + "#" + rectangle[1];
            arr[1] = rectangle[0] + "#" + rectangle[3];
            arr[2] = rectangle[2] + "#" + rectangle[3];
            arr[3] = rectangle[2] + "#" + rectangle[1];
            area += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
            for (int i = 0; i < 4; i++) {
                if (set.contains(arr[i]))
                    set.remove(arr[i]);
                else
                    set.add(arr[i]);
            }
            arr = new String[4];
        }
        if (set.size() != 4)
            return false;
        int i = 0;
        for (String s : set) arr[i++] = s;
        Arrays.sort(arr);
        String[] a = arr[0].split("#");
        String[] b = arr[3].split("#");
        return area == (Integer.parseInt(b[0]) - Integer.parseInt(a[0])) * (Integer.parseInt(b[1]) - Integer.parseInt(a[1]));
    }
}