//
// @lc app=leetcode.cn id=961 lang=java
//
// [961] long-pressed-name
//
class Solution {
    public static boolean isLongPressedName(String name, String typed) {
        char[] c1 = name.toCharArray();
        char[] c2 = typed.toCharArray();
        int i = 0, j = 0;
        while (i < c1.length && j < c2.length) {
            char temp1 = c1[i];
            char temp2 = c2[j];
            int count1 = 0;
            int count2 = 0;
            while (i < c1.length && c1[i] == temp1) {
                count1++;
                i++;
            }
            while (j < c2.length && c2[j] == temp2) {
                count2++;
                j++;
            }
            System.out.println(temp1 + "->" + temp2);
            System.out.println(count1 + "->" + count2);
            System.out.println(i + "->" + c1.length);
            System.out.println(j + "->" + c2.length);
            if (count1 > count2 || temp1 != temp2)
                return false;
        }
        if (i != c1.length || j != c2.length)
            return false;
        return true;
    }
}
// @lc code=end