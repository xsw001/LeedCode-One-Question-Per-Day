package Tiger2022.March;
/*
440. 字典序的第K小数字
给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。



示例 1:

输入: n = 13, k = 2
输出: 10
解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
示例 2:

输入: n = 1, k = 1
输出: 1


提示:

1 <= k <= n <= 109
通过次数21,278提交次数53,801
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 字典序的第K小数字_440 {


    @Test
    public void test() throws Exception {
        for (int i = 0; i <= 130; i++) {
            System.out.println(findKthNumber(130, i));
        }
    }

    class Node {
        List<Node> nodes;
        int count;
        int val;

        public Node() {
            nodes = new ArrayList<>();
            val = -1;
        }
    }

    int ans, index;

    // chao 内存
    public int findKthNumber(int n, int k) {
        ans = 0;
        index = 0;
        Node node = new Node();
        node.val = 0;
        dfs(node, n, k);
        return ans;
    }

    private void dfs(Node root, int n, int k) {
        if (ans > 0)
            return;
        if (k == index) {
            ans = root.val;
            return;
        }
        if (index > k || root.val > n)
            return;
        for (int i = 0; i < 10; i++) {
            int num = root.val * 10 + i;
            if (num == 0) continue;
            if (num > n)
                return;
            Node node = new Node();
            node.val = num;
            root.nodes.add(node);
            ++index;
            dfs(node, n, k);
        }
    }

    class Solution {
        public int findKthNumber(int n, int k) {
            int curr = 1;
            k--;
            while (k > 0) {
                int steps = getSteps(curr, n);
                if (steps <= k) {
                    k -= steps;
                    curr++;
                } else {
                    curr = curr * 10;
                    k--;
                }
            }
            return curr;
        }

        public int getSteps(int curr, long n) {
            int steps = 0;
            long first = curr;
            long last = curr;
            while (first <= n) {
                steps += Math.min(last, n) - first + 1;
                first = first * 10;
                last = last * 10 + 9;
            }
            return steps;
        }

        // 该函数实现了统计范围 [1, limit] 内以 x 为前缀的数的个数。
        int getCnt(int x, int limit) {
            String a = String.valueOf(x), b = String.valueOf(limit);
            int n = a.length(), m = b.length(), k = m - n;
            int ans = 0, u = Integer.parseInt(b.substring(0, n));
            for (int i = 0; i < k; i++)
                ans += Math.pow(10, i);
            if (u > x)
                ans += Math.pow(10, k);
            else if (u == x)
                ans += limit - x * Math.pow(10, k) + 1;
            return ans;
        }

    }

}
