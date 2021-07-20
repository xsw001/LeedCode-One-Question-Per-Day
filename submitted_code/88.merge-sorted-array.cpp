//
// @lc app=leetcode.cn id=88 lang=cpp
//
// [88] merge-sorted-array
//
class Solution {
public:
    void merge(vector<int>& nums1, int m, vector<int>& nums2, int n) {
        if (m == 0) {
            nums1 = nums2;
        } else {
            int end = m + n - 1;
            --m;
            for (int i = n - 1; i >= 0; ) {
                nums1[end--] = nums1[m] > nums2[i] ? nums1[m--] : nums2[i--];
                if(m < 0){
                    for (int j = i; j >= 0; j--){
                        nums1[end--] = nums2[j];
                    }
                    return;
                }
            }
        }
    }
};
// @lc code=end