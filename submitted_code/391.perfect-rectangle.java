//
// @lc app=leetcode.cn id=391 lang=java
//
// [391] perfect-rectangle
//
class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        HashSet<String> set = new HashSet<>();
        int area = 0;
        String[] arr = new String[4];
        for (int[] rectangle : rectangles) {
            arr[0] = rectangle[0] + "#" + rectangle[1];
            arr[1] = rectangle[0] + "#" + rectangle[3];
            arr[2] = rectangle[2] + "#" + rectangle[3];
            arr[3] = rectangle[2] + "#" + rectangle[1];
            area += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
            for (int i = 0; i < 4; i++) {
                if (set.contains(arr[i]))
                    set.remove(arr[i]);
                else
                    set.add(arr[i]);
            }
            arr = new String[4];
        }
        if (set.size() != 4)
            return false;
        int i = 0;
        for (String s : set) arr[i++] = s;
        Arrays.sort(arr);
        String[] a = arr[0].split("#");
        String[] b = arr[3].split("#");
        return area == (Integer.parseInt(b[0]) - Integer.parseInt(a[0])) * (Integer.parseInt(b[1]) - Integer.parseInt(a[1]));
    }
}
// @lc code=end