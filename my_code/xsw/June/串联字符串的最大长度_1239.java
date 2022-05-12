package xsw.June;
/*

 */

import java.util.*;

public class 串联字符串的最大长度_1239 {

    // 思路错误，又不一定是连续的串
    public static int maxLength1(List<String> arr) {
        int l = arr.size();
        int[][] dp = new int[l][l];
        for (int i = 0; i < l; i++) {
            HashSet<Character> set = new HashSet<>();
            String s = arr.get(i);
            boolean f = true;
            for (int t = 0; t < s.length(); t++) {
                if (!set.add(s.charAt(t))) {
                    f = false;
                    break;
                }
            }
            if (f)
                dp[i][i] = set.size();
            for (int j = i + 1; j < l; j++) {
                String ss = arr.get(j);
                boolean flag = true;
                for (int k = 0; k < ss.length(); k++) {
                    if (!set.add(ss.charAt(k))) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + ss.length());
                else
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
            }
        }
        return dp[0][l - 1];
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        String[] s = {"cha", "r", "act", "ers"};
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(s));
        System.out.println(maxLength(list));
    }

    // huisu 回溯
    /*执行用时：1574 ms, 在所有 Java 提交中击败了5.05%的用户*/
    public static int maxLength(List<String> arr) {
        List<List<String>> res = new ArrayList<>();
        backward(res, new ArrayList<String>(), arr, 0);
        //System.out.println(res);
        int ans = 0;
        for (List<String> list : res) {
            HashSet<Character> set = new HashSet<>();
            int num = 0;
            for (String s : list) {
                num += s.length();
                for (int i = 0; i < s.length(); i++) {
                    set.add(s.charAt(i));
                }
            }
            if (num == set.size())
                ans = Math.max(ans, num);
        }
        return ans;
    }

    private static void backward(List<List<String>> res, ArrayList<String> path, List<String> arr, int start) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < arr.size(); i++) {
            path.add(arr.get(i));
            backward(res, path, arr, i + 1);
            path.remove(path.size() - 1);
        }
    }

    // 新奇的思路  用树
    int maxLength2(List<String> arr) {
        // 当作哈希状态表，记录每个小写字母出现的次数
        int[] m = new int[26];
        // 以0号元素为根结点，开始回溯。
        return dfs(arr, 0, m);
    }

    int dfs(List<String> arr, int childIndex, int[] m) {
        if (childIndex == arr.size()) {
            return 0;
        }
        // 再定义一个状态表来保存加入当前字符串之后的状态
        int[] t = new int[26];
        System.arraycopy(m, 0, t, 0, m.length);
        if (isUnique(arr.get(childIndex), t)) {
            int curLen = arr.get(childIndex).length();
            int len1 = curLen + dfs(arr, childIndex + 1, t);
            int len2 = dfs(arr, childIndex + 1, m);
            return Math.max(len1, len2);
        }
        return dfs(arr, childIndex + 1, m);
    }

    /*
    判断加入字符串s后，是否满足不含相同字符
    注意对于哈希表传入的是引用
    */
    boolean isUnique(String s, int[] t) {
        for (int i = 0; i < s.length(); i++) {
            t[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (t[i] > 1) {
                return false;
            }
        }
        return true;
    }
    /*哈希表使用位压缩代替
由题可知，字符串中只含有小写字母（最多只需使用26位来存储每个字符的存在状态）。

使用一个36位的int来存储结果字符串的状态，初始化为0（开始时每位都为0，0～25位分别表示a～z）。int m = 0;

每来一个字符c先通过i = c-'a'获取它的对应位i，然后判断m的第i位是否为0.
如果为0，说明该位对应字符还未出现；否则该字符已存在，返回false。
*/
    boolean isUnique1(String s, int t) {
        for (char c : s.toCharArray()) {
            int i = c-'a';	//获取字符对应位
            //判断该位是否为0
            if ((t & (1<<i)) != 0) {
                return false;
            }
            t |= (1<<i);
        }
        return true;
    }

}