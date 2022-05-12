//
// @lc app=leetcode.cn id=218 lang=java
//
// [218] the-skyline-problem
//
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<Integer> boundaries = new ArrayList<>();
        for (int[] building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        Collections.sort(boundaries);

        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int boundary : boundaries) {
            int max = 0;
            for (int[] building : buildings) {
                if (building[0] <= boundary && building[1] > boundary)
                    max = Math.max(max, building[2]);
                if(building[0] > boundary)
                    break;
            }
            if (ret.size() == 0 || max != ret.get(ret.size() - 1).get(1)) {
                ret.add(Arrays.asList(boundary, max));
            }
        }
        return ret;
    }
}
// @lc code=end