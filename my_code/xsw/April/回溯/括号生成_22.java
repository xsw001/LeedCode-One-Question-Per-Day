package xsw.April.回溯;
/*
22. 括号生成
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。



示例 1：

输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]
示例 2：

输入：n = 1
输出：["()"]


提示：

1 <= n <= 8
通过次数437,853提交次数566,060
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class 括号生成_22 {

    @Test
    public void test() throws Exception {

        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};

        List<Integer> list = new ArrayList<>();
        System.out.println(generateParenthesis(3).size());
    }

    List<String> ans;

    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        dfs(new StringBuilder(), 0, 0, n);
        return ans;
    }

    private void dfs(StringBuilder sb, int l, int r, int n) {
        if (sb.length() == n * 2) {
            ans.add(sb.toString());
            return;
        }
        if (l < n) {
            sb.append('(');
            dfs(sb, l + 1, r, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (r < l) {
            sb.append(')');
            dfs(sb, l, r + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
