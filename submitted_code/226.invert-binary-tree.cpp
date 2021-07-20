//
// @lc app=leetcode.cn id=226 lang=cpp
//
// [226] invert-binary-tree
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
    TreeNode* invertTree(TreeNode* root) {
        if(!root || (!root->left && !root->right))
            return root;
        invertTree(root->left);
        TreeNode *temp=root->right;
        root->right = root->left;
        root->left = temp;
        invertTree(root->left);
        return root;
    }
};
// @lc code=end