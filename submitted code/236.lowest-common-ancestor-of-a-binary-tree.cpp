//
// @lc app=leetcode.cn id=236 lang=cpp
//
// [236] lowest-common-ancestor-of-a-binary-tree
//
class Solution {
public:
    bool getpath(TreeNode* root,TreeNode* node,stack<TreeNode*>& path)
    {
        if(root == NULL)
            return false;
        path.push(root);
        if(root == node)
            return true;
        
        if(getpath(root->left,node,path))
            return true;
        if(getpath(root->right,node,path))
            return true;
        
        path.pop();
        return false;       
    }
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        stack<TreeNode*> ppath;
        stack<TreeNode*> qpath;
        
        getpath(root,p,ppath);
        getpath(root,q,qpath);
        
        while(ppath.size() != qpath.size())
        {
            if(ppath.size() > qpath.size())
                ppath.pop();
            else
                qpath.pop();
        }
        
        while(ppath.top() != qpath.top())
        {
            ppath.pop();
            qpath.pop();
        }
        return ppath.top();
    }
};
//先写一个子函数分别求出p q两个节点的路径存入栈中，再比较栈中元素，找到第一个相同的节点，即为二者的最近公共祖先节点
// @lc code=end