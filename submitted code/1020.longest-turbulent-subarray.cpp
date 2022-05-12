//
// @lc app=leetcode.cn id=1020 lang=cpp
//
// [1020] longest-turbulent-subarray
//
class Solution {
public:
    int maxTurbulenceSize(vector<int>& arr) {
        int m = 1;
        int l = arr.size();
        if (l == 1)
            return 1;
        int flag[l - 1];
        int num = 0;
        for (int i = 0; i < l - 1; i++) {
            if (arr[i] < arr[i + 1])
                flag[i] = 0;
            else if (arr[i] > arr[i + 1])
                flag[i] = 1;
            else {
                flag[i] = 2;
                ++num;
            }
        }
        if(num == l-1)
            return 1;
        for (int i = 0; i < l; i++) {
            int temp = 1;
            while (i < l - 2 && flag[i] != flag[i + 1] && flag[i] != 2 && flag[i+1] != 2) {
                ++i;
                ++temp;
            }
            m = max(m, temp);
        }
        return m + 1;
    }
};
// @lc code=end