//
// @lc app=leetcode.cn id=1995 lang=java
//
// [1995] finding-pairs-with-a-certain-sum
//
class FindSumPairs {
        int[] one;
        int[] two;
        HashMap<Integer, Integer> map = new HashMap<>();

        public FindSumPairs(int[] nums1, int[] nums2) {
            one = nums1;
            two = nums2;
            for (int s : nums2) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }

        public void add(int index, int val) {

            if (map.get(two[index]) == 1)
                map.remove(two[index]);
            else {
                map.put(two[index], map.get(two[index]) - 1);
            }
            two[index] += val;
            map.put(two[index], map.getOrDefault(two[index], 0) + 1);
        }

        public int count(int tot) {
            int res = 0;
            for (int i : one) {
                int rest = tot - i;
                if(map.containsKey(rest))
                    res += map.get(rest);
            }
            return res;
        }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */
// @lc code=end