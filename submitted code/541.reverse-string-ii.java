//
// @lc app=leetcode.cn id=541 lang=java
//
// [541] reverse-string-ii
//
class Solution {

    public static String reverseStr(String s, int k) {
        int n = s.length();
        if (n < k)
            return new StringBuilder(s).reverse().toString();
        if (n < k * 2)
            return reverseK(s, k);
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index + 2 * k <= n) {
            sb.append(reverseK(s.substring(index, index + 2 * k), k));
            index += 2 * k;
        }
        if (n != sb.length()) {
            String substring = s.substring(sb.length(), n);
            if (n - sb.length() < k)
                sb.append(new StringBuilder(substring).reverse());
            else
                sb.append(reverseK(substring, k));
        }
        return sb.toString();
    }

    private static String reverseK(String s, int k) {
        return new StringBuilder(s.substring(0, k)).reverse().append(s.substring(k)).toString();
    }
}
// @lc code=end