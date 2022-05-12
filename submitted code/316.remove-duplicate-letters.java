//
// @lc app=leetcode.cn id=316 lang=java
//
// [316] remove-duplicate-letters
//
class Solution {
    public String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        int len = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
            lastIndex[chars[i]-'a'] = i;
        }
        boolean[] visited = new boolean[26];
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            if(visited[chars[i]-'a'])
                continue;

            while(!list.isEmpty() && list.peekLast()>chars[i] && lastIndex[list.peekLast()-'a']>i) {
                Character last = list.pollLast();
                visited[last-'a'] = false;
            }

            list.addLast(chars[i]);
            visited[chars[i]-'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : list) {
            sb.append(c);
        }
        return sb.toString();
    }
}
// @lc code=end