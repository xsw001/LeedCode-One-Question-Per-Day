//
// @lc app=leetcode.cn id=111 lang=c
//
// [111] minimum-depth-of-binary-tree
//
int minDepth(struct TreeNode* root){
    if(root==NULL)
        return 0;
    int min=1;
    struct TreeNode* queue[10000];
    int front=-1,rear=-1;
    int last=0;
    struct TreeNode* p;
    queue[++rear]=root;
    while(front < rear){
        p=queue[++front];
        if(p->left==NULL && p->right==NULL)
            break;
        if(p->left!=NULL)
            queue[++rear]=p->left;
        if(p->right!=NULL)
            queue[++rear]=p->right;
        if(front==last){
            last=rear;
            min++;
        }
    }
    return min;
}
// @lc code=end