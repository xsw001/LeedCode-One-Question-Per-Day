//
// @lc app=leetcode.cn id=599 lang=java
//
// [599] minimum-index-sum-of-two-lists
//
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        ArrayList<String> list = new ArrayList<>();
        int minIndexSum = Integer.MAX_VALUE;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                if (map.get(list2[i]) + i < minIndexSum) {
                    list.clear();
                    list.add(list2[i]);
                    minIndexSum = map.get(list2[i]) + i;
                } else if (map.get(list2[i]) + i == minIndexSum) {
                    list.add(list2[i]);
                }
            }
        }
        return list.toArray(new String[0]);
    }
}
// @lc code=end