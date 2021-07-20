//
// @lc app=leetcode.cn id=451 lang=java
//
// [451] sort-characters-by-frequency
//
class Solution {
    public String frequencySort(String s) {
            Map<Character, Integer> map = new HashMap<Character, Integer>();
            int maxFreq = 0;
            int length = s.length();
            for (int i = 0; i < length; i++) {
                char c = s.charAt(i);
                int frequency = map.getOrDefault(c, 0) + 1;
                map.put(c, frequency);
                maxFreq = Math.max(maxFreq, frequency);
            }

            StringBuffer[] buckets = new StringBuffer[maxFreq + 1];
            for (int i = 0; i <= maxFreq; i++) {
                buckets[i] = new StringBuffer();
            }
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                char c = entry.getKey();
                int frequency = entry.getValue();
                buckets[frequency].append(c);
            }

            StringBuilder sb = new StringBuilder();
            for (int i = maxFreq; i > 0; i--) {
                StringBuffer bucket = buckets[i];
                int size = bucket.length();
                for (int j = 0; j < size; j++) {
                    sb.append(String.valueOf(bucket.charAt(j)).repeat(Math.max(0, i)));
                }
            }
            return sb.toString();
    }
}
// @lc code=end