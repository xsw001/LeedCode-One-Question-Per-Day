//
// @lc app=leetcode.cn id=373 lang=java
//
// [373] find-k-pairs-with-smallest-sums
//
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<ArrayList<Integer>> lists = new PriorityQueue<>((o1, o2) -> o1.get(0) + o1.get(1) - o2.get(0) - o2.get(1));
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            for (int j = 0; j < Math.min(nums2.length, k); j++) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                lists.offer(list);
            }
        }
        for (int i = 0; i < k && !lists.isEmpty(); i++)
            ans.add(lists.poll());
        return ans;
    }
}
// @lc code=end