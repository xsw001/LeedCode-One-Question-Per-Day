//
// @lc app=leetcode.cn id=384 lang=java
//
// [384] shuffle-an-array
//
class Solution {

    int[] original;
    ArrayList<Integer> cur;
    int n;
    
    public Solution(int[] nums) {
        n = nums.length;
        original = Arrays.copyOf(nums, n);
        cur = new ArrayList<>();
        for (int num : nums) {
            cur.add(num);
        }
    }

    public int[] reset() {
        return original;
    }

    public int[] shuffle() {
        Collections.shuffle(cur);

        return  cur.stream().mapToInt(Integer::intValue).toArray();
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
// @lc code=end