//
// @lc app=leetcode.cn id=437 lang=cpp
//
// [437] path-sum-iii
//
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    int nums = 0;
    int pathSum(TreeNode* root, int sum) {
        if(!root)
            return 0;
        path(root,sum);
        pathSum(root->left,sum);
        pathSum(root->right,sum);
        return nums;
    }
    void path(TreeNode *root,int sum){
        if(!root)
            return;
        sum -= root->val;
        if(sum == 0)
            ++nums;
        path(root->left,sum);
        path(root->right,sum);
    }
};
// @lc code=end