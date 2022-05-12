package Tiger2022.January;
/*
1220. 统计元音字母序列的数目
给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：

字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
每个元音 'a' 后面都只能跟着 'e'
每个元音 'e' 后面只能跟着 'a' 或者是 'i'
每个元音 'i' 后面 不能 再跟着另一个 'i'
每个元音 'o' 后面只能跟着 'i' 或者是 'u'
每个元音 'u' 后面只能跟着 'a'
由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。



示例 1：

输入：n = 1
输出：5
解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
示例 2：

输入：n = 2
输出：10
解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
示例 3：

输入：n = 5
输出：68


提示：

1 <= n <= 2 * 10^4
通过次数11,399提交次数19,039
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class 统计元音字母序列的数目_1220 {

    // 严重超时
    public int countVowelPermutation1(int n) {
        HashMap<Character, String> map = new HashMap<>();
        map.put('a', "e");
        map.put('e', "ai");
        map.put('i', "aeou");
        map.put('o', "iu");
        map.put('u', "a");
        int ans = 5;
        LinkedList<Character> list = new LinkedList<>();
        Collections.addAll(list, 'a', 'e', 'i', 'o', 'u');
        for (int i = 1; i < n; i++) {
            int size = list.size();
            for (int j = 0; j < size; j++) {
                Character key = list.pollFirst();
                for (char v : map.get(key).toCharArray()) {
                    list.addLast(v);
                }
            }
            ans = list.size() % 1000000007;
        }
        return ans;
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(countVowelPermutation(1));
        for (int i = 1; i < 40; i++) {
            System.out.println(countVowelPermutation(i));
        }
    }

    // 动归
    public int countVowelPermutation(int n) {
        int m = 1000000007;
        long a = 1, e = 1, i = 1, o = 1, u = 1;
        for (int j = 2; j <= n; j++) {
            long aa = (e + i + u) % m;
            long ee = (a + i) % m;
            long ii = (e + o) % m;
            long oo = (i) % m;
            long uu = (i + o) % m;
            a = aa;
            e = ee;
            i = ii;
            o = oo;
            u = uu;
        }
        return (int) ((a + e + i + o + u) % m);
    }
}