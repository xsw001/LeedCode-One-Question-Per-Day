//
// @lc app=leetcode.cn id=275 lang=java
//
// [275] h-index-ii
//
class Solution {
    public int hIndex(int[] citations) {
        int size = citations.length;
        int l = 0, r = size;
        while (l < r){
            int mid = (l+r)/2+1;
            if(citations[size-mid] >= mid)
                l = mid;
            else
                r = mid-1;

        }
        return l;
    }
}
// @lc code=end