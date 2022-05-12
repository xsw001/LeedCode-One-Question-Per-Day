//
// @lc app=leetcode.cn id=112 lang=c
//
// [112] path-sum
//
bool hasPathSum(struct TreeNode* root, int sum){
    if(!root)
        return 0;
    
    /*init stack*/
    struct TreeNode* s[100];
    int top = -1;

    struct TreeNode *p = root, *last= NULL; //last保存上次访问过的节点
    while(p || top >= 0){
        if(p){
            s[++top] = p;
            p = p->left;
        }
        else{
            p = s[top];
            if(p->right && p->right != last){
                p = p->right;
                s[++top] = p;
                p = p->left;
            }
            else{
                if(!p->left && !p->right){  //叶子节点
                    int tmp = 0;
                    for(int i = 0; i <= top; i++){
                        tmp += s[i]->val;   //栈中所有祖先之和
                    }
                    if(tmp == sum)
                        return 1;
                }
                last = p;
                top--;
                p = NULL;
            }
        }
    }
    return 0;
}
// @lc code=end