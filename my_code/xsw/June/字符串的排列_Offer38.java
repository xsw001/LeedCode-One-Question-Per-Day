package xsw.June;
/*
剑指 Offer 38. 字符串的排列
输入一个字符串，打印出该字符串中字符的所有排列。



你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。



示例:

输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]


限制：

1 <= s 的长度 <= 8

通过次数107,990提交次数190,363
 */

import java.util.Arrays;
import java.util.HashSet;

public class 字符串的排列_Offer38 {

    static int a =0 ;

    public static String[] permutation(String s) {
        boolean[] flag = new boolean[s.length()];
        HashSet<String> set = new HashSet<>();
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        back(chars, set, new StringBuilder(), flag);
        return set.toArray(new String[0]);
    }

    private static void back(char[] arr, HashSet<String> set, StringBuilder sb, boolean[] flag) {
        if (sb.length() == arr.length) {
            set.add(sb.toString());
        }
        for (int i = 0; i < arr.length; i++) {
            // 剪了很多
            /*设定一个规则，保证在填每一个空位的时候重复字符只会被填入一次。
            具体地，我们首先对原字符串排序，保证相同的字符都相邻，
            在递归函数中，我们限制每次填入的字符一定是这个字符所在重复字符集合中「从左往右第一个未被填入的字符」*/
            //例如两个c，标记为c0和c1。这个条件就是要先用了c0，才能考虑用c1
            //否则会产生... c0... c1...和...c1...c0....两种重复的结果
            /* s[I] s[I-1] 是两个相同的字符，他们俩只有两种相对位置关系，要么s[i]在前 要么s[i-1]在前。
             !vis[i-1]意思是s[i-1]没有被访问过，这时候直接在回溯的track中添加了s[i]，
             这是s[i]在前的情况，剪掉。其实剪掉s[i-1]在前的情况也可以，
             只要把 !vis[i-1] 改成 vis[i-1] 即可，也可以通过*/
            if (flag[i] || (i > 0 && !flag[i - 1] && arr[i - 1] == arr[i])) {
                continue;
            }
            sb.append(arr[i]);
            flag[i] = true;
            back(arr, set, sb, flag);
            sb.deleteCharAt(sb.length() - 1);
            flag[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(permutation("aadddaa")));
        System.out.println(a);
    }

}