package xsw.April;
/*
面试题 17.21. 直方图的水量
给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。



上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）

示例:

输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class hard_面试题1721 {

    /*执行用时：1322 ms, 在所有 Java 提交中击败了5.01%的用户
    内存消耗：38 MB, 在所有 Java 提交中击败了83.22%的用户*/
    public static int trap1(int[] height) {
        int res = 0;
        int l = height.length;
        while (true) {
            int flag = 0;
            for (int i = 0; i < l; ) {
                if (height[i] == 0) {
                    ++i;
                    ++flag;
                } else {
                    int left = i;
                    while (left < l && height[left] != 0) {
                        --height[left];
                        ++left;
                    }
                    int right = left;
                    while (right < l && height[right] == 0)
                        ++right;
                    if (right < l)
                        res += right - left;
                    i = right;
                }
            }
            if (flag == l)
                break;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(data));

    }

    //总体积减去柱子体积就是水的容量
    public static int trap2(int[] height) {
        int len = height.length;
        int total = 0;
        for(int i : height)
            total += i;
        int l =0 ,r = len-1;
        int h = 1;
        int v = 0;
        while(l <= r){
            while(l <= r && height[l] < h)
                ++l;
            while(l <= r && height[r] < h)
                --r;
            v += r-l+1;
            ++h;
        }
        return v-total;
    }
    /*
    对于每根柱子而言，我们只需要找出「其左边最高的柱子」和「其右边最高的柱子」。
    对左右的最高柱子取一个最小值，再和当前柱子的高度做一个比较，即可得出当前位置可以接下的雨水。
    同时，边缘的柱子不可能接到雨水（某一侧没有柱子）
    */
    public static int trap3(int[] height) {
        int n = height.length;
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            int cur = height[i];

            // 获取当前位置的左边最大值
            int l = Integer.MIN_VALUE;
            for (int j = i - 1; j >= 0; j--) l = Math.max(l, height[j]);
            if (l <= cur) continue;

            // 获取当前位置的右边边最大值
            int r = Integer.MIN_VALUE;
            for (int j = i + 1; j < n; j++) r = Math.max(r, height[j]);
            if (r <= cur) continue;

            // 计算当前位置可接的雨水
            ans += Math.min(l, r) - cur;
        }
        return ans;
    }

    //使用动态数组优化
    public static int trap4(int[] height) {
        int n = height.length;
        int ans = 0;
        int[] lm = new int[n];
        lm[0] = height[0];
        for (int i = 1; i < n; i++) {
            lm[i] = Math.max(lm[i-1],height[i]);
        }
        int[] rm = new int[n];
        rm[n-1] = height[n-1];
        for (int i = n-2; i > 0; --i) {
            rm[i] = Math.max(rm[i+1],height[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            int cur = height[i];
            // 计算当前位置可接的雨水
            ans += Math.min(lm[i], rm[i]) - cur;
        }
        return ans;
    }

    //单调栈
    public static int trap(int[] height) {
        int n = height.length;
        int ans = 0;
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!d.isEmpty() && height[i] > height[d.peekLast()]) {
                int cur = d.pollLast();

                // 如果栈内没有元素，说明当前位置左边没有比其高的柱子，跳过
                if (d.isEmpty()) continue;

                // 左右位置，并有左右位置得出「宽度」和「高度」
                int l = d.peekLast(), r = i;
                int w = r - l + 1 - 2;
                int h = Math.min(height[l], height[r]) - height[cur];
                ans += w * h;
            }
            d.addLast(i);
        }
        return ans;
    }
}