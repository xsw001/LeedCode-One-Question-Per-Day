package xsw.Nov_Dec;/*
49. 字母异位词分组
给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

示例:

输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
说明：

所有输入均为小写字母。
不考虑答案输出的顺序。
*/

import java.util.*;

public class LeedCode49 {
    /*  执行结果：通过
        执行用时：1299 ms, 在所有 Java 提交中击败了 5.00% 的用户
        内存消耗：41.9 MB, 在所有 Java 提交中击败了 30.43% 的用户
    */
    public static List<List<String>> groupAnagrams1(String[] strs) {
        List<List<String>> listList = new ArrayList<List<String>>();
        ArrayList<String> str = new ArrayList<>(Arrays.asList(strs));
        while (!str.isEmpty()) {
            String temp = str.get(0);
            ArrayList<String> list = new ArrayList<>();
            list.add(temp);
            str.remove(temp);
            for (int i = 0; i < str.size(); i++) {
                String s = str.get(i);
                if (isAnagram(temp, s)) {
                    list.add(s);
                    str.remove(s);
                    --i;
                }
            }
            listList.add(list);
        }
        return listList;
    }

    private static boolean isAnagram(String temp, String s) {
        int[] array = new int[26];
        if (temp.length() != s.length())
            return false;
        for (int i = 0; i < temp.length(); i++) {
            ++array[temp.charAt(i) - 'a'];
            --array[s.charAt(i) - 'a'];
        }
        for (int i : array) {
            if (i != 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> anagrams = groupAnagrams(strs);
        System.out.println(anagrams);
    }
    //通过，不过还是十分耗时
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> listList = new ArrayList<List<String>>();
        if (strs.length == 0) {
            return listList;
        }
        ArrayList<String> list = new ArrayList<>();
        list.add(strs[0]);
        listList.add(list);
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < listList.size(); j++) {
                if (isAnagram(strs[i], listList.get(j).get(0))) {
                    listList.get(j).add(strs[i]);
                    break;
                }
            }
            if (j == listList.size()) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(strs[i]);
                listList.add(temp);
            }
        }
        return listList;
    }
    //排序+哈希表   我想到了，我认为复杂度很高，结果比自己的简单多了
    public static List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
    //自己想的时候，是把相同的作为map的value，但是没想到让共同属性作为建，想的是随便一个数字，所以自己认为实现太复杂，就否决了
    // 模式识别：一旦需要根据特征进行分类，就应该用散列表
    // 此题中，单词与键的映射关系
    public static List<List<String>> groupAnagrams3(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
