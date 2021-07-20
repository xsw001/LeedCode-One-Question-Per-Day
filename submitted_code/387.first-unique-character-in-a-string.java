//
// @lc app=leetcode.cn id=387 lang=java
//
// [387] first-unique-character-in-a-string
//
class Solution {
    public int firstUniqChar(String s) {
        int[] arr = new int[26];
        int  len = s.length();
        for(int i = 0; i< len ;++i){
            ++arr[s.charAt(i)-'a'];
        }
        for(int i = 0; i< len ;++i){
            if(arr[s.charAt(i)-'a']==1)
                return i;
        }
        return -1;
    }
}
// @lc code=end