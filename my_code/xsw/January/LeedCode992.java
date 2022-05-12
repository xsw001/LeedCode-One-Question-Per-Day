package xsw.January;
/*
992. K 个不同整数的子数组
给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。

（例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）

返回 A 中好子数组的数目。



示例 1：

输入：A = [1,2,1,2,3], K = 2
输出：7
解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
示例 2：

输入：A = [1,2,1,3,4], K = 3
输出：3
解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].


提示：

1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length
 */

import java.util.Arrays;
import java.util.HashSet;

public class LeedCode992 {

    //超时,42 / 55 个通过测试用例
    public static int subarraysWithKDistinct1(int[] A, int K) {
        int len = A.length;
        HashSet<Integer> set = new HashSet<>();

        int result = 0;
        for (int i = 0; i <= len - K; i++) {
            for (int k = i; k < K + i; k++) {
                set.add(A[k]);
            }
            for (int j = i + K; j < len; j++) {
                if (set.size() == K)
                    ++result;
                set.add(A[j]);
            }
            if (set.size() == K)
                ++result;
            set.clear();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] A = {2, 1, 1, 1, 2};
        int K = 1;
        System.out.println(subarraysWithKDistinct(A, K));
    }

    //超时,45 / 55 个通过测试用例
    public static int subarraysWithKDistinct2(int[] A, int K) {
        int len = A.length;
        int result = 0, before = 0;
        int[] visited = new int[len + 1];
        for (int window = K; window <= len; ++window) {
            int windowSize = 0;
            for (int i = 0; i < window; i++) {
                if (visited[A[i]] == 0)
                    ++windowSize;
                ++visited[A[i]];
            }
            for (int i = window; i < len; i++) {
                if (windowSize == K)
                    ++result;
                if (visited[A[i]] == 0) {
                    ++visited[A[i]];
                    --visited[A[i - window]];
                    ++windowSize;
                    if (visited[A[i - window]] == 0)
                        --windowSize;
                } else if (A[i] != A[i - window]) {
                    ++visited[A[i]];
                    --visited[A[i - window]];
                    if (visited[A[i - window]] == 0)
                        --windowSize;
                }
            }
            if (windowSize == K)
                ++result;
            Arrays.fill(visited, 0);
        }
        return result;
    }

    //超时,48 / 55 个通过测试用例
    public static int subarraysWithKDistinct3(int[] A, int K) {
        int len = A.length;
        int result = 0, before = 0;
        int[] visited = new int[len + 1];
        for (int window = K; window <= len; ++window) {
            int windowSize = 0;
            for (int i = 0; i < window; i++) {
                if (visited[A[i]] == 0)
                    ++windowSize;
                ++visited[A[i]];
            }
            int tmp = 0;
            for (int i = window; i < len; i++) {
                if (windowSize == K)
                    ++result;
                else if (windowSize > K)
                    ++tmp;
                if (visited[A[i]] == 0) {
                    ++visited[A[i]];
                    --visited[A[i - window]];
                    ++windowSize;
                    if (visited[A[i - window]] == 0)
                        --windowSize;
                } else if (A[i] != A[i - window]) {
                    ++visited[A[i]];
                    --visited[A[i - window]];
                    if (visited[A[i - window]] == 0)
                        --windowSize;
                }
            }
            if (windowSize == K)
                ++result;
            else if (windowSize > K)
                ++tmp;
            if (tmp == len - window + 1)
                break;
            Arrays.fill(visited, 0);
        }
        return result;
    }

    public static int subarraysWithKDistinct(int[] A, int K) {
        return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
    }

    private static int atMostKDistinct(int[] A, int K) {
        int len = A.length;
        int[] freq = new int[len + 1];

        int left = 0;
        int right = 0;
        // [left, right) 里不同整数的个数
        int count = 0;
        int res = 0;
        // [left, right) 包含不同整数的个数小于等于 K
        while (right < len) {
            if (freq[A[right]] == 0) {
                count++;
            }
            freq[A[right]]++;
            right++;

            while (count > K) {
                freq[A[left]]--;
                if (freq[A[left]] == 0) {
                    count--;
                }
                left++;
            }
            // [left, right) 区间的长度就是对结果的贡献
            res += right - left;
        }
        return res;
    }

    public static int subarraysWithKDistincts(int[] A, int K) {
        int l = A.length;
        int[] num1 = new int[l + 1];
        int[] num2 = new int[l + 1];
        int top1 = 0, top2 = 0;
        int left1 = 0,left2 = 0,right = 0;
        int res = 0;
        while (right < l){
            if(num1[A[right]] == 0){
                ++top1;
            }
            ++num1[A[right]];
            if(num2[A[right]] == 0){
                ++top2;
            }
            ++num2[A[right]];
            while(top1 > K){
                num1[A[left1]]--;
                if(num1[A[left1]] == 0)
                    --top1;
                ++left1;
            }
            while(top2 > K-1){
                num2[A[left2]]--;
                if(num2[A[left2]] == 0)
                    --top2;
                ++left2;
            }
            res += left2-left1;
            ++right;
        }
        return res;
    }
}