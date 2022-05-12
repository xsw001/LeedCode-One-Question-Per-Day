//
// @lc app=leetcode.cn id=861 lang=cpp
//
// [861] flipping-an-image
//
class Solution {
public:
    void swap(vector<int> &vec){
        int i=0,j=vec.size()-1;
        while(i<j){
            int temp = vec[i];
            vec[i++] = vec[j];
            vec[j--] = temp;
        }
    }
    void reverse(vector<int> &vec){
        int i=0;
        while(i < vec.size()){
            if(vec[i] == 1)
                vec[i++] = 0;
            else if(vec[i] == 0)
                vec[i++] = 1;
        }
    }
    vector<vector<int>> flipAndInvertImage(vector<vector<int>>& A) {
        for(int i = 0;i<A.size();++i){
            swap(A[i]);
            reverse(A[i]);
        }
        return A;
    }
};
// @lc code=end