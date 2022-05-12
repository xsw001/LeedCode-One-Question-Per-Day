//
// @lc app=leetcode.cn id=145 lang=cpp
//
// [145] binary-tree-postorder-traversal
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
    vector<int> postorderTraversal(TreeNode* root) {
        vector<int> res;
        if(root==NULL)
            return res;
        TreeNode *stack1[100],*stack2[100];
        int top1=-1,top2=-1;
        stack1[++top1]=root;
        TreeNode* temp;
        while(top1 != -1){
            temp = stack1[top1--];
            stack2[++top2] = temp;
            if(temp->left)
                stack1[++top1] = temp->left;
            if(temp->right)
                stack1[++top1] = temp->right;
        }
        while(top2 != -1){
            res.push_back(stack2[top2--]->val);
        }
        return res;
    }
} ;
// @lc code=end