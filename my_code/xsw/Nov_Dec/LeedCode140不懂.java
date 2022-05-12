package xsw.Nov_Dec;/*
140. 单词拆分 II
给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。
返回所有这些可能的句子。
     说明：

        分隔时可以重复使用字典中的单词。
        你可以假设字典中没有重复的单词。

        示例 1：

        输入:
        s = "catsanddog"
        wordDict = ["cat", "cats", "and", "sand", "dog"]
        输出:
        [
          "cats and dog",
          "cat sand dog"
        ]
            示例 2：

            输入:
            s = "pineapplepenapple"
            wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
            输出:
            [
              "pine apple pen apple",
              "pineapple pen apple",
              "pine applepen apple"
            ]
            解释: 注意你可以重复使用字典中的单词。
*/

import java.util.*;

public class LeedCode140不懂 {
    //  自己的代码是错误的
    public static String helper(String s, List<String> wordDict) {
        StringBuilder result = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (String word : wordDict) {
                if (s.equals(word)) {
                    result.append(word);
                    return String.valueOf(result);
                }
                if (s.startsWith(word)) {
                    result.append(word).append(" ");
                    s = s.substring(word.length());
                }
                Collections.shuffle(wordDict);
            }
        }

        return String.valueOf(result);
    }
    public static List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            Collections.shuffle(wordDict);
            String helper = helper(s, wordDict);
            String[] words = helper.split(" ");
            int len = 0;
            for (String word : words) {
                len += word.length();
            }
            if (len == s.length()) {
                set.add(helper);
            }
        }
        return new ArrayList<>(set);
    }
    /*-----------------------------------官方答案--------------------------------------------------*/
    public static List<String> wordBreak1(String s, List<String> wordDict) {
        Map<Integer, List<List<String>>> map = new HashMap<Integer, List<List<String>>>();
        List<List<String>> wordBreaks = backtrack(s, s.length(), new HashSet<String>(wordDict), 0, map);
        List<String> breakList = new LinkedList<String>();
        for (List<String> wordBreak : wordBreaks) {
            breakList.add(String.join(" ", wordBreak));
        }
        return breakList;
    }

    public static List<List<String>> backtrack(String s, int length, Set<String> wordSet, int index, Map<Integer, List<List<String>>> map) {
        if (!map.containsKey(index)) {
            List<List<String>> wordBreaks = new LinkedList<List<String>>();
            if (index == length) {
                wordBreaks.add(new LinkedList<String>());
            }
            for (int i = index + 1; i <= length; i++) {
                String word = s.substring(index, i);
                if (wordSet.contains(word)) {
                    List<List<String>> nextWordBreaks = backtrack(s, length, wordSet, i, map);
                    for (List<String> nextWordBreak : nextWordBreaks) {
                        LinkedList<String> wordBreak = new LinkedList<String>(nextWordBreak);
                        wordBreak.offerFirst(word);
                        wordBreaks.add(wordBreak);
                    }
                }
            }
            map.put(index, wordBreaks);
        }
        return map.get(index);
    }
    /*---------------------------------比官方答案的速度更快--------------------------------------------*/

    public static List<String> wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        int maxLength = 0;
        for (String word : wordDict) {
            set.add(word);
            maxLength = Math.max(maxLength, word.length());
        }
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;

        for (int i = 1; i <= length; i++) {
            for (int j = i; j >= 0 && j >= i - maxLength; j--) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        List<String> res = new ArrayList<>();
        if (dp[length]) {
            LinkedList<String> list = new LinkedList<>();
            dfs(s, length, set, dp, list, res);
        }

        return res;
    }

    private static void dfs(String s, int end, Set<String> set, boolean[] dp, LinkedList<String> list, List<String> res) {
        if (end == 0) {
            StringBuilder sb = new StringBuilder();
            for (String word : list) {
                sb.append(word).append(" ");
            }
            sb.setLength(sb.length() - 1);
            res.add(sb.toString());
        }

        for (int i = 0; i < end; i++) {
            if (dp[i]) {
                String substring = s.substring(i, end);
                if (set.contains(substring)) {
                    list.addFirst(substring);
                    dfs(s, i, set, dp, list, res);
                    list.removeFirst();
                }
            }
        }
    }
    /*----------------------------------------------------------------------------------------*/

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String[] words = {"a","aa","aaa","aaaa","aaaaa"};
        ArrayList<String> wordDict = new ArrayList<>(Arrays.asList(words));
        for (String s1 : wordBreak2(s, wordDict)) {
            System.out.println(s1);
        }
        System.out.println(wordBreak2(s, wordDict).size());
        String[] ss = {"a a a a a a a a","aa a a a a a a","a aa a a a a a","a a aa a a a a","aa aa a a a a","aaaa a a a a","a a a aa a a a","aa a aa a a a","a aa aa a a a","a aaaa a a a","a a a a aa a a","aa a a aa a a","a aa a aa a a","a a aa aa a a","aa aa aa a a","aaaa aa a a","a a aaaa a a","aa aaaa a a","a a a a a aa a","aa a a a aa a","a aa a a aa a","a a aa a aa a","aa aa a aa a","aaaa a aa a","a a a aa aa a","aa a aa aa a","a aa aa aa a","a aaaa aa a","a a a aaaa a","aa a aaaa a","a aa aaaa a","a a a a a a aa","aa a a a a aa","a aa a a a aa","a a aa a a aa","aa aa a a aa","aaaa a a aa","a a a aa a aa","aa a aa a aa","a aa aa a aa","a aaaa a aa","a a a a aa aa","aa a a aa aa","a aa a aa aa","a a aa aa aa","aa aa aa aa","aaaa aa aa","a a aaaa aa","aa aaaa aa","a a a a aaaa","aa a a aaaa","a aa a aaaa","a a aa aaaa","aa aa aaaa","aaaa aaaa"};
        System.out.println(new ArrayList<>(Arrays.asList(ss)).size());
    }
}