package xsw.Nov_Dec;/*
139. 单词拆分
给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

说明：

拆分时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。
示例 1：

输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
示例 2：

输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     注意你可以重复使用字典中的单词。

*/

import java.util.*;

public class LeedCode139迷糊 {
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
            }
        }
        return String.valueOf(result);
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        for (int i = 0; i < 100; i++) {
            Collections.shuffle(wordDict);
            String helper = helper(s, wordDict);
            String[] words = helper.split(" ");
            int len = 0;
            for (String word : words) {
                len += word.length();
            }
            if (len == s.length()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "cacars1crscars";
        String[] words = {"car", "ca", "rs"};
        ArrayList<String> wordDict = new ArrayList<>(Arrays.asList(words));
        System.out.println(wordBreak2(s, wordDict));
    }

    /*    public static boolean wordBreak1(String s, List<String> wordDict) {
            for (int c = 0; c < 3; c++) {
                Collections.shuffle(wordDict);
                while (!s.equals("")) {
                    System.out.println(s);
                    int i = 0;
                    int flag = 0;
                    for (; i < wordDict.size(); ++i) {
                        if (s.startsWith(wordDict.get(i))) {
                            s = s.substring(wordDict.get(i).length());
                            ++flag;
                        }
                    }
                    if (i == wordDict.size() && flag == 0) {
                        System.out.println(s);
                        return false;
                    }
                    if (s.equals(""))
                        return true;
                }
            }
            return true;
        }*/
    public static boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static boolean wordBreak2(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); ++i) {
            for (int j = 0; j < i; ++j) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    // DFS
    public boolean wordBreak3(String s, List<String> wordDict) {
        boolean[] visited = new boolean[s.length() + 1];
        return dfs(s, 0, wordDict, visited);
    }
    private boolean dfs(String s, int start, List<String> wordDict, boolean[] visited) {
        for (String word : wordDict) {
            int nextStart = start + word.length();
            if (nextStart > s.length() || visited[nextStart]) {
                continue;
            }

            if (s.indexOf(word, start) == start) {
                if (nextStart == s.length() || dfs(s, nextStart, wordDict, visited)) {
                    return true;
                }
                visited[nextStart] = true;
            }
        }
        return false;
    }
}
