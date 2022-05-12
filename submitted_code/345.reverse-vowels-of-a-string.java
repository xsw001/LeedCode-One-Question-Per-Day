//
// @lc app=leetcode.cn id=345 lang=java
//
// [345] reverse-vowels-of-a-string
//
class Solution {
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int n = s.length();
        int l = 0, r = n - 1;
        while (l < r) {
            while (l < n - 1 && s.charAt(l) != 'i' && s.charAt(l) != 'e' && s.charAt(l) != 'a' && s.charAt(l) != 'o' && s.charAt(l) != 'u' && s.charAt(l) != 'I' && s.charAt(l) != 'E' && s.charAt(l) != 'A' && s.charAt(l) != 'O' && s.charAt(l) != 'U')
                ++l;
            while (r > 0 && s.charAt(r) != 'i' && s.charAt(r) != 'e' && s.charAt(r) != 'a' && s.charAt(r) != 'o' && s.charAt(r) != 'u' && s.charAt(r) != 'I' && s.charAt(r) != 'E' && s.charAt(r) != 'A' && s.charAt(r) != 'O' && s.charAt(r) != 'U')
                --r;
            if (l < r) {
                char c = arr[l];
                arr[l] = arr[r];
                arr[r] = c;
                ++l;
                --r;
            }
        }
        return new String(arr);
    }
}
// @lc code=end