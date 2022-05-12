//
// @lc app=leetcode.cn id=575 lang=java
//
// [575] distribute-candies
//
class Solution {
    public int distributeCandies(int[] candyType) {
        HashSet set = new HashSet<Integer>();
        for(int i : candyType)
            set.add(i);
        return set.size() > candyType.length/2 ? candyType.length/2 : set.size();
    }
}
// @lc code=end