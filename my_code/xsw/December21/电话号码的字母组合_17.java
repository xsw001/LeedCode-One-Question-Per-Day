package xsw.December21;
/*
17. 电话号码的字母组合
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。





示例 1：

输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
示例 2：

输入：digits = ""
输出：[]
示例 3：

输入：digits = "2"
输出：["a","b","c"]


提示：

0 <= digits.length <= 4
digits[i] 是范围 ['2', '9'] 的一个数字。
 */

import org.junit.Test;

import java.util.*;

public class 电话号码的字母组合_17 {

    public List<String> letterCombinations(String digits) {
        ArrayList<String> ans = new ArrayList<>();
        if (digits.equals(""))
            return ans;
        HashMap<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        int l = digits.length();
        for (char c : map.get(digits.charAt(0)).toCharArray()) {
            ans.add(String.valueOf(c));
        }
        for (int i = 1; i < l; i++) {
            ArrayList<String> list = new ArrayList<>();
            for (char c1 : map.get(digits.charAt(i)).toCharArray()) {
                for (String an : ans) {
                    list.add(an + c1);
                }
            }
            ans = new ArrayList<>(list);
        }

        return ans;
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(letterCombinations("23"));
    }

}