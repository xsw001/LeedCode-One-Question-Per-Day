package xsw.July;
/*
451. 根据字符出现频率排序
给定一个字符串，请将字符串里的字符按照出现的频率降序排列。

示例 1:

输入:
"tree"

输出:
"eert"

解释:
'e'出现两次，'r'和't'都只出现一次。
因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
示例 2:

输入:
"cccaaa"

输出:
"cccaaa"

解释:
'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
注意"cacaca"是不正确的，因为相同的字母必须放在一起。
示例 3:

输入:
"Aabb"

输出:
"bbAa"

解释:
此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
注意'A'和'a'被认为是两种不同的字符。
通过次数52,513提交次数77,006
 */

import java.util.*;

public class 根据字符出现频率排序_451 {

    // 按照出现频率排序
    public static String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        ArrayList<Character> list = new ArrayList<>(map.keySet());
        list.sort((o1, o2) -> map.get(o2) - map.get(o1));
        for (Character c : list)
            sb.append(String.valueOf(c).repeat(Math.max(0, map.get(c))));
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("5645as64fs4df1as5d46"));

    }

    //桶排序
    static class Solution {
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

        public String frequencySortt(String s) {
            int[] counts = new int[128];
            char[] chars = s.toCharArray();
            for (char c : chars) {
                ++counts[c];
            }
            int[][]countArr = new int[128][];
            int index = 0;
            for (int i = 0; i < 128; ++i) {
                if (counts[i] > 0) {
                    countArr[index++] = new int[]{counts[i], i};
                }
            }
            int resIndex = 0;
            quickSort(countArr, 0, index - 1);
            for (int i  = 0; i < index; ++i) {
                char c = (char) countArr[i][1];
                for (int j = 0; j < countArr[i][0]; ++j) {
                    chars[resIndex++] = c;
                }
            }
            return new String(chars);
        }

        private void quickSort(int[][] arrs, int left, int right) {
            if (left >= right) {
                return;
            }
            swap(arrs, left, (left + right) >> 1);
            int index = left;
            for (int i = left + 1; i <= right; ++i) {
                if (arrs[i][0] >= arrs[left][0]) {
                    swap(arrs, ++index, i);
                }
            }
            swap(arrs, left, index);
            quickSort(arrs, left, index - 1);
            quickSort(arrs, index + 1, right);
        }

        private void swap(int[][] arrs, int left, int right) {
            int[] tmp = arrs[left];
            arrs[left] = arrs[right];
            arrs[right] = tmp;
        }
    }
}