//
// @lc app=leetcode.cn id=917 lang=java
//
// [917] boats-to-save-people
//
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int person : people) {
            map.put(person, map.getOrDefault(person, 0) + 1);
        }
        int num = 0;
        while (map.size() > 0) {
            ++num;
            int w = map.lastKey();
            if (map.get(w) == 1)
                map.remove(w);
            else
                map.put(w, map.get(w) - 1);
            Integer w1 = map.floorKey(limit - w);
            if (w1 != null)
                if (map.get(w1) == 1)
                    map.remove(w1);
                else
                    map.put(w1, map.get(w1) - 1);
        }
        return num;
    }
}
// @lc code=end