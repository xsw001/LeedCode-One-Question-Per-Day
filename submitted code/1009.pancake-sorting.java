//
// @lc app=leetcode.cn id=1009 lang=java
//
// [1009] pancake-sorting
//
class Solution {
    public List<Integer> pancakeSort(int[] arr) {
        int target = arr.length;
        ArrayList<Integer> ans = new ArrayList<>();
        while (target > 0) {
            for (int i = 0; i < target; i++) {
                if (arr[i] == target && i != target - 1) {
                    reverse(arr, i);
                    ans.add(i+1);
                    reverse(arr, target - 1);
                    ans.add(target);
                    break;
                }
            }
            --target;
        }
        return ans;
    }

    private void reverse(int[] arr, int index) {
        int l = 0, r = index;
        while (l < r) {
            int t = arr[r];
            arr[r] = arr[l];
            arr[l] = t;
            ++l;
            --r;
        }
    }
}
// @lc code=end