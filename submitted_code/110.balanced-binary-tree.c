//
// @lc app=leetcode.cn id=110 lang=c
//
// [110] balanced-binary-tree
//
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

int high(struct TreeNode* t){
    if(t==NULL)
        return 0;
    int l=high(t->left);
    if(l==-1)return -1;
    int r=high(t->right);
    if(r==-1)return -1;
    if(abs(l-r)<2)
        return l>r?l+1:r+1;
    else return -1;
}
bool isBalanced(struct TreeNode* root){
    return high(root)!=-1;
}
// @lc code=end