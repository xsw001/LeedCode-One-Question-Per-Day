//
// @lc app=leetcode.cn id=659 lang=java
//
// [659] split-array-into-consecutive-subsequences
//
class Solution {
    public boolean isPossible(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();  
		for(int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		for(int num : nums) {
			int len = 0, preCount = 1, next = num;			
			while(map.getOrDefault(next, 0) >= preCount) {
				preCount = map.get(next);
				map.put(next, preCount - 1);
				len++;
				next++;
			}
			if(len > 0 && len < 3) return false;
		}
		return true;
    }
}
// @lc code=end