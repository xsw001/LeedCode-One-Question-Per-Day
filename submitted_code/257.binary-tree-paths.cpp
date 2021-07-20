//
// @lc app=leetcode.cn id=257 lang=cpp
//
// [257] binary-tree-paths
//
class Solution {
public:
    vector<string> binaryTreePaths(TreeNode* root) {
        vector<string> res;
        if(root == NULL)
            return res;
        if(root->left == NULL && root->right == NULL){  //root为叶子结点
            res.push_back(to_string(root->val));
        }

        vector<string> leftS = binaryTreePaths(root->left);
        for(int i = 0; i < leftS.size(); i ++)
            res.push_back(to_string(root->val) + "->" + leftS[i]);
        vector<string> rihgtS = binaryTreePaths(root->right);
        for(int i = 0; i < rihgtS.size(); i ++)
            res.push_back(to_string(root->val) + "->" + rihgtS[i]);
        return res;
    }
};
// @lc code=end