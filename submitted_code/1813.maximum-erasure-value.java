//
// @lc app=leetcode.cn id=1813 lang=java
//
// [1813] maximum-erasure-value
//
class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        int maxNum = 0;
        for (Integer i : set) {
            maxNum += i;
        }
        LinkedList<Integer> list = new LinkedList<>();
        int temp = 0;
        int max = 0;
        for (int num : nums) {
            if (!list.contains(num)) {
                list.addFirst(num);
                temp += num;
            } else {
                max = Math.max(max, temp);
                if(maxNum == max)
                    return maxNum;
                while(list.peekLast()!=num){
                    temp -= list.peekLast();
                    list.pollLast();
                }
                list.pollLast();
                list.addFirst(num);
            }
        }
        return Math.max(max, temp);
    }
}
// @lc code=end