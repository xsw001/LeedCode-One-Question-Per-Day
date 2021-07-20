//
// @lc app=leetcode.cn id=107 lang=c
//
// [107] binary-tree-level-order-traversal-ii
//
int** levelOrderBottom(struct TreeNode* root, int* returnSize, int** returnColumnSizes){
    struct TreeNode * queue[10000] = {NULL};
    int **res=(int **)malloc(sizeof(int *)*10000); 
    int resSize=0;
    int front=-1,rear=-1;
    int last=0;
    queue[++rear]=root;
    struct TreeNode *p;
    res[resSize]=(int *)malloc(sizeof(int)*10000);
    int k=0;
    *returnColumnSizes=(int *)malloc(sizeof(int)*10000);
      if(root==NULL){
        *returnSize=0;
          return res;
      }
    while(front<rear)
    {
        p=queue[++front];
        res[resSize][k]=p->val;
        k++;
        if(p->left)
        {
            queue[++rear]=p->left;    
        }
        if(p->right)
        {
            queue[++rear]=p->right;
        }
        if(front==last)
        {
            last=rear;
            (*returnColumnSizes)[resSize]=k;
            resSize++;
             res[resSize]=(int *)malloc(sizeof(int)*17000);
            k=0;
        }
    }
    int j=resSize-1;
    for(int i=0;i<j;i++)
    {
        int *temp;
        temp=res[i];
        res[i]=res[j];
        res[j]=temp;
        int t;
        t=(*returnColumnSizes)[i];
        (*returnColumnSizes)[i]=(*returnColumnSizes)[j];
        (*returnColumnSizes)[j]=t;
        j--;
    }
    *returnSize=resSize;
    return res;
}
// @lc code=end