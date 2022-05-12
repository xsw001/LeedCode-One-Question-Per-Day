//
// @lc app=leetcode.cn id=94 lang=cpp
//
// [94] binary-tree-inorder-traversal
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
    vector<int> inorderTraversal(TreeNode* root) {
        vector<int> result;
        TreeNode *stack[100];
        int top=-1;
        TreeNode *p = root;
        while (p || top!=-1)
        {
            if (p) {
                stack[++top]=p;
                p = p->left;
            }
            else
            {
                p=stack[top--];
                result.push_back(p->val);
                p = p->right;
            }
        }
        return result;
    }
};
// @lc code=end