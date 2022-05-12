//
// @lc app=leetcode.cn id=404 lang=cpp
//
// [404] sum-of-left-leaves
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
    int sum = 0;
    int sumOfLeftLeaves(TreeNode* root) {
        if(!root)
            return sum;
        if(root->left!=NULL && root->left->left==NULL && root->left->right==NULL)
            sum += root->left->val;
        sumOfLeftLeaves(root->left);
        sumOfLeftLeaves(root->right);
        return sum;
    }
};
// @lc code=end