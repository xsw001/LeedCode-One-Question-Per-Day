//
// @lc app=leetcode.cn id=397 lang=java
//
// [397] integer-replacement
//
class Solution {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    public int integerReplacement(int n) {
        if (n == 1)
            return 0;
        if (!map.containsKey(n)) {
            if (n % 2 == 0)
                map.put(n, 1+integerReplacement(n / 2));
            else 
                map.put(n,2 + Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1)));
        }
        return map.get(n);
    }
}
// @lc code=end