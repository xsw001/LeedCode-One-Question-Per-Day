//
// @lc app=leetcode.cn id=108 lang=c
//
// [108] convert-sorted-array-to-binary-search-tree
//
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
struct TreeNode* generateBST(int *nums,int left,int right){
    if(left>right) return NULL;
    int mid = (left+right)/2;
    struct TreeNode* p;
    p = (struct TreeNode*) malloc(sizeof(struct TreeNode));
    p->val = nums[mid];
    p->left = generateBST(nums,left,mid-1);
    p->right = generateBST(nums,mid+1,right);
    return p;
}

struct TreeNode* sortedArrayToBST(int* nums, int numsSize){
    struct TreeNode * t = generateBST(nums,0,numsSize-1);
    return t;
}
// @lc code=end