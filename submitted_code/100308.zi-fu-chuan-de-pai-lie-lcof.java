//
// @lc app=leetcode.cn id=100308 lang=java
//
// [100308] zi-fu-chuan-de-pai-lie-lcof
//
class Solution {
    public static String[] permutation(String s) {
        boolean[] flag = new boolean[s.length()];
        HashSet<String> set = new HashSet<>();
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        back(chars, set, new StringBuilder(), flag);
        return set.toArray(new String[0]);
    }

    private static void back(char[] arr, HashSet<String> set, StringBuilder sb, boolean[] flag) {
        if (sb.length() == arr.length) {
            set.add(sb.toString());
        }
        for (int i = 0; i < arr.length; i++) {
            if (flag[i] || (i > 0 && !flag[i - 1] && arr[i - 1] == arr[i])) {
                continue;
            }
            if (!flag[i]) {
                sb.append(arr[i]);
                flag[i] = true;
                back(arr, set, sb, flag);
                sb.deleteCharAt(sb.length() - 1);
                flag[i] = false;
            }
        }
    }
}
// @lc code=end