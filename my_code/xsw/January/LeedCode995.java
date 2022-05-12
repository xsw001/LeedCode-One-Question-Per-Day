package xsw.January;
/*
995. K 连续位的最小翻转次数
在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。

返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。



示例 1：

输入：A = [0,1,0], K = 1
输出：2
解释：先翻转 A[0]，然后翻转 A[2]。
示例 2：

输入：A = [1,1,0], K = 2
输出：-1
解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
示例 3：

输入：A = [0,0,0,1,0,1,1,0], K = 3
输出：3
解释：
翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]


提示：

1 <= A.length <= 30000
1 <= K <= A.length
 */

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class LeedCode995 {

    //97 / 110 个通过测试用例   超时
    public static int minKBitFlips(int[] A, int K) {
        int l = A.length;
        int[] a = new int[l + 3];
        a[0] = a[1] = a[2] = 1;
        System.arraycopy(A, 0, a, 3, l);
        int right = 3, left = 0, res = 0;
        while (right <= l+3-K) {
            System.out.println(Arrays.toString(a));
            if (a[right] == 0) {
                left = right;
                ++res;
                int i = 0;
                while (right < l + 3 && i < K) {
                    if (a[right] == 0)
                        a[right] = 1;
                    else
                        a[right] = 0;
                    ++right;
                    ++i;
                }
                right = left + 1;
            } else {
                ++right;
                ++left;
            }
        }
        for (int i = a.length-1; i >= a.length-K; i--) {
            if (a[i] == 0)
                return -1;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] A = {0,1,0,0,1,0};
        int K = 4;
        System.out.println(minKBitFlips1(A, K));
    }
//方法一：差分数组
    /*考虑不去翻转数字，而是统计每个数字需要翻转的次数。对于一次翻转操作，相当于把子数组中所有数字的翻转次数加 1。*/
    //用差分数组的思想来 计算当前数字需要翻转的次数
    public static int minKBitFlips1(int[] A, int K) {
        int n = A.length;
        int[] diff = new int[n + 1];
        /*
        diff[i] 表示两个相邻元素 A[i−1] 和 A[i] 的翻转次数的差
        对于区间 [l,r]，将其元素全部加 1，只会影响到 l 和 r+1 处的差分值
        故 diff[l] 增加 1，diff[r+1] 减少 1。
        */
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            revCnt += diff[i];//通过累加差分数组可以得到当前位置需要翻转的次数
            if ((A[i] + revCnt) % 2 == 0) {
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                ++revCnt;
                ++diff[i];//可以省略，因为用不到
                --diff[i + K];
            }
        }
        return ans;
    }
    //方法二：滑动窗口
    public int minKBitFlips2(int[] A, int K) {
        int n = A.length;
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            if (i >= K && A[i - K] > 1) {
                revCnt ^= 1;
                A[i - K] -= 2; // 复原数组元素，若允许修改数组 A，则可以省略
            }
            if (A[i] == revCnt) {
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                revCnt ^= 1;
                A[i] += 2;
            }
        }
        return ans;
    }
    public int minKBitFlips3(int[] A, int K) {
        int res = 0;
        Deque<Integer> que = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            if (que.size() > 0 && i > que.peek() + K - 1) {
                que.removeFirst();
            }
            //1.本来是1，翻转奇数次变为0，所以需要再次翻转，放入队列
            //2.本来是0，翻转偶数次还是0，所以需要再次翻转，放入队列
            if (que.size() % 2 == A[i]) {
                if (i + K > A.length) return -1;
                que.add(i);
                res += 1;
            }
        }
        return res;
    }

}