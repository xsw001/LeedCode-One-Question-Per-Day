//
// @lc app=leetcode.cn id=1360 lang=java
//
// [1360] maximum-length-of-a-concatenated-string-with-unique-characters
//
class Solution {
// 新奇的思路  用树
    int maxLength(List<String> arr) {
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
}
// @lc code=end