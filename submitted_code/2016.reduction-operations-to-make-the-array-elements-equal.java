//
// @lc app=leetcode.cn id=2016 lang=java
//
// [2016] reduction-operations-to-make-the-array-elements-equal
//
class Solution {
    public int reductionOperations(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        // 对 nums 去重， 并且倒序排序
        Set<Integer> keys = new TreeSet<Integer>((x, y) -> {return y - x;});

        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            keys.add(num);
        }

        // 计算
        int count = 0, sum = 0;
        for(int key : keys) {
            sum += map.get(key);
            count += sum; // 可以看成 累加 每个最大值的次数，然后最大值变成次大值，一直循环。多加了全是最小值，要减去
        }
        return count - sum;
    }
}
// @lc code=end