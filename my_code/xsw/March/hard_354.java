package xsw.March;
/*
354. 俄罗斯套娃信封问题
给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

说明:
不允许旋转信封。

示例:

输入: envelopes = []
输出: 3
解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class hard_354 {

    //33 / 85 个通过测试用例   思路错误，困难题哪有这么简单
    public static int maxEnvelopes1(int[][] envelopes) {
        if (envelopes.length == 0)
            return 0;
        Arrays.sort(envelopes, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        System.out.println(Arrays.deepToString(envelopes));
        int res = 1;
        int left = 0, right = 1;
        while (right < envelopes.length) {
            if (envelopes[right][0] > envelopes[left][0] && envelopes[right][1] > envelopes[left][1]) {
                ++res;
                left = right;
            }
            ++right;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] data = {{2, 100}, {3, 200}, {4, 300}, {5, 500}, {5, 400}, {5, 250}, {6, 370}, {6, 360}, {7, 380}};
        System.out.println(maxEnvelopes(data));
    }

    //40 / 85 个通过测试用例
    public static int maxEnvelopes2(int[][] envelopes) {
        if (envelopes.length == 0)
            return 0;
        Arrays.sort(envelopes, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        int res = 1;
        int left = 0, right = 1;
        while (right < envelopes.length) {
            if (envelopes[right][0] > envelopes[left][0] && envelopes[right][1] > envelopes[left][1]) {
                ++res;
                left = right;
            }
            ++right;
        }
        int res1 = 1;
        int l = envelopes.length - 1, r = envelopes.length - 2;
        while (r >= 0) {
            if (envelopes[r][0] < envelopes[l][0] && envelopes[r][1] < envelopes[l][1]) {
                ++res1;
                l = r;
            }
            --r;
        }
        return Math.max(res, res1);
    }
    //通过，可以优化
    //执行用时：294 ms, 在所有 Java 提交中击败了 24.14% 的用户
    public static int maxEnvelopes(int[][] envelopes) {
        int l = envelopes.length;
        if (l == 0)
            return 0;
        Arrays.sort(envelopes, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        int[] num = new int[l];
        num[0] = 1;
        int right = 1;
        int res = 0;
        while (right < l) {
            int left = right - 1;
            while (left >= 0) {
                if (envelopes[right][0] > envelopes[left][0] && envelopes[right][1] > envelopes[left][1]) {
                    num[right] = Math.max(num[right], num[left] + 1);
                }
                --left;
            }
            if (num[right] == 0)
                num[right] = 1;
            res = Math.max(res, num[right]);
            ++right;
        }
        return res;
    }
    //官方的和我的思路一模一样
    public int maxEnvelopes3(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            }
        });

        int[] f = new int[n];
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
    //优化 ：基于二分查找的动态规划
    public int maxEnvelopes4(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            }
        });

        List<Integer> f = new ArrayList<Integer>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < n; ++i) {
            int num = envelopes[i][1];
            if (num > f.get(f.size() - 1)) {
                f.add(num);
            } else {
                int index = binarySearch(f, num);
                f.set(index, num);
            }
        }
        return f.size();
    }

    public int binarySearch(List<Integer> f, int target) {
        int low = 0, high = f.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (f.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}