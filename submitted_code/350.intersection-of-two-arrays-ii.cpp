//
// @lc app=leetcode.cn id=350 lang=cpp
//
// [350] intersection-of-two-arrays-ii
//
class Solution {
public:
    vector<int> intersect(vector<int>& nums1, vector<int>& nums2) {
        sort(nums1.begin(),nums1.end());
        sort(nums2.begin(),nums2.end());
        vector<int> result;
        size_t i=0,j=0;
        while(i < nums1.size() && j<nums2.size()){
            if(nums1[i]==nums2[j]){
                result.push_back(nums1[i]);
                ++i;
                ++j;
            }else if(nums1[i] < nums2[j]){
                ++i;
            }else if(nums1[i] > nums2[j]){
                ++j;
            }
        }
        return result;
    }
};
// vector<int> result;
//         size_t i=0;
//         int here= nums1.size()-1;
//         for(;i < nums2.size();++i){
//             for(size_t j=0;j<here;++j){
//                 if(nums2[i]==nums1[j]){
//                     result.push_back(nums2[i]);
//                     int temp=nums1[j];
//                     nums1[j]=nums1[here];
//                     nums1[here--]=temp;
//                     ++i;++j;
//                 }
//             }
            
//         }
//         return result;
// @lc code=end