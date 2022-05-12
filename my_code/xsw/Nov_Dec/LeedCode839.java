package xsw.Nov_Dec;
/*
839. 相似字符串组
如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。

例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。

总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。

给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？



示例 1：

输入：strs = ["tars","rats","arts","star"]
输出：2
示例 2：

输入：strs = ["omv","ovm"]
输出：1


提示：

1 <= strs.length <= 100
1 <= strs[i].length <= 1000
sum(strs[i].length) <= 2 * 104
strs[i] 只包含小写字母。
strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。


备注：

      字母异位词（anagram），一种把某个字符串的字母的位置（顺序）加以改换所形成的新词。
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LeedCode839 {

    public static int numSimilarGroups(String[] strs) {
        int n = strs.length;
        Union union = new Union(n);
        HashMap<Integer, ArrayList<String>> similar = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        list.add(strs[0]);
        similar.put(0, list);
        for (int i = 1; i < n; i++) {
            int flag = 0;
            String t = strs[i];
            for (Integer index : similar.keySet()) {
                ArrayList<String> strings = similar.get(index);
                for (String s : strings) {
                    if (numSimilar(t, s)) {
                        union.union(i, index);
                        strings.add(t);
                        similar.put(index, strings);
                        ++flag;
                        break;
                    }
                }

            }
            if (flag == 0) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(t);
                similar.put(i, temp);
            }
        }
        return union.size;
    }

    //不全面
    private static boolean numSimilar1(String t, String s) {
        if (s.equals(t))
            return true;
        int l = t.length();
        int n = 0;
        for (int i = 0; i < l; i++) {
            if (t.charAt(i) != s.charAt(i)) {
                if (s.charAt(t.lastIndexOf(s.charAt(i))) != t.charAt(i))
                    return false;
                ++n;
            }
        }
        return n == 2;
    }
    //超时
    private static boolean numSimilar2(String t, String s) {
        if (s.equals(t))
            return true;
        if (s.length() != t.length())
            return false;
        ArrayList<String> list = new ArrayList<>();
        char[] chars = t.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                char[] tc = Arrays.copyOf(chars,chars.length);
                if (tc[i] != tc[j]) {
                    char c = tc[i];
                    tc[i] = tc[j];
                    tc[j] = c;
                    StringBuilder temp= new StringBuilder();
                    for (char cc : tc) {
                        temp.append(cc);
                    }
                    list.add(temp.toString());
                }
            }
        }
        return list.contains(s);
    }
    public static boolean numSimilar(String a, String b) {
        int len = a.length();
        int num = 0;
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                num++;
                if (num > 2) {
                    return false;
                }
            }
        }
        return true;
    }
    static class Union {
        int[] parent;
        int[] rank;
        int size;

        public Union(int n) {
            this.size = n;
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            return x == parent[x] ? x : (x = find(parent[x]));
        }

        public boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb)
                return false;
            if (rank[pa] < rank[pb]) {
                int temp = pa;
                pa = pb;
                pb = temp;
            }
            parent[pb] = pa;
            rank[pa]++;
            --size;
            return true;
        }

        public boolean isCon(int a, int b) {
            return find(a) == find(b);
        }
    }

    public static void main(String[] args) {
        String[] strs = {"tars", "rats", "arts", "star", "star"};
//        for (int i = 0; i < strs.length - 1; i++) {
//            System.out.println(numSimilar(strs[i], strs[i + 1]));
//        }
//        System.out.println(numSimilar("zqzzz", "zzqzz"));
        System.out.println(numSimilarGroups(strs));

    }
}
