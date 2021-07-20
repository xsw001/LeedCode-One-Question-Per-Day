//
// @lc app=leetcode.cn id=100 lang=c
//
// [100] same-tree
//
bool same(struct TreeNode* p, struct TreeNode* q)
{
    if(!p || !q)
        return 0;
    if(p->val == q->val)
        return 1;
    else
        return 0;
}

bool isSameTree(struct TreeNode* p, struct TreeNode* q)
{
    if(!p && !q)
        return 1;
    if(!same(p, q))
        return 0;

    /*init queue*/
    struct TreeNode* q1[100];
    struct TreeNode* q2[100];
    int front1, rear1, front2, rear2;
    front1 = rear1 = front2 = rear2 = -1;
    q1[++rear1] = p;
    q2[++rear2] = q;

    struct TreeNode *a, *b;
    while(front1 < rear1){
        a = q1[++front1];
        b = q2[++front2];
        if(!same(a, b))
            return 0;
        if(a->left || b->left){
            q1[++rear1] = a->left;
            q2[++rear2] = b->left;
        }
        if(a->right || b->right){
            q1[++rear1] = a->right;
            q2[++rear2] = b->right;
        }
    }
    return 1;
}
// @lc code=end