//
// @lc app=leetcode.cn id=403 lang=java
//
// [403] frog-jump
//
class Solution {
    public boolean canCross(int[] stones) {
        if (stones[1] != 1)
            return false;
        int l = stones.length;
        for (int i = 1; i < l; ++i) {
            if (stones[i] - stones[i - 1] > i) {
                return false;
            }
        }
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        HashSet<Integer> list = new HashSet<>();
        list.add(0);
        map.put(0, list);
        for (int i = 1; i < l; i++) {
            HashSet<Integer> temp = new HashSet<>();
            for (int j = i - 1; j >= 0; j--) {
                int high = stones[i] - stones[j];
                if(high > j+1)
                    break;
                HashSet<Integer> diff = map.get(j);
                for (Integer d : diff) {
                    if (d != -1 && Math.abs(d - high) < 2)
                        temp.add(high);
                }
            }
            if (temp.isEmpty()) {
                temp.add(-1);
            }
            map.put(i, temp);
        }
        return map.get(l - 1).iterator().next() != -1;
    }
}
// @lc code=end