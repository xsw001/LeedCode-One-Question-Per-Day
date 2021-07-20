//
// @lc app=leetcode.cn id=104 lang=c
//
// [104] maximum-depth-of-binary-tree
//
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */


int maxDepth(struct TreeNode* root){
    if(root==NULL)
        return 0;
    int i=maxDepth(root->left);
    int j=maxDepth(root->right);
    return i>j?i+1:j+1;
}
// @lc code=end