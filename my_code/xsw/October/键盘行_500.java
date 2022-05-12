package xsw.October;
/*
500. 键盘行
给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。

美式键盘 中：

第一行由字符 "qwertyuiop" 组成。
第二行由字符 "asdfghjkl" 组成。
第三行由字符 "zxcvbnm" 组成。
American keyboard



示例 1：

输入：words = ["Hello","Alaska","Dad","Peace"]
输出：["Alaska","Dad"]
示例 2：

输入：words = ["omk"]
输出：[]
示例 3：

输入：words = ["adsdf","sfd"]
输出：["adsdf","sfd"]


提示：

1 <= words.length <= 20
1 <= words[i].length <= 100
words[i] 由英文字母（小写和大写字母）组成
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class 键盘行_500 {

    static String[] keyboard = {"qwertyuiopQWERTYUIOP", "asdfghjklASDFGHJKL", "zxcvbnmZXCVBNM"};

    public static String[] findWords(String[] words) {
        ArrayList<String> ans = new ArrayList<>();
        for (String word : words) {
            if (find(word))
                ans.add(word);
        }
        return ans.toArray(new String[0]);
    }

    private static boolean find(String word) {
        int a = 0, b = 0, c = 0;
        for (int i = 0; i < word.length(); i++) {
            if (keyboard[0].indexOf(word.charAt(i)) != -1) {
                ++a;
                if (b != 0 || c != 0)
                    return false;
            } else if (keyboard[1].indexOf(word.charAt(i)) != -1) {
                ++b;
                if (a != 0 || c != 0)
                    return false;
            } else {
                ++c;
                if (b != 0 || a != 0)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] data = {"Hello", "Alaska", "Dad", "Peace"};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(Arrays.toString(findWords(data)));
    }

}