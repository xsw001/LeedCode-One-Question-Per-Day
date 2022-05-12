//
// @lc app=leetcode.cn id=66 lang=c
//
// [66] plus-one
//
int* plusOne(int* digits, int digitsSize, int* returnSize){
    int i=digitsSize-1,num=0;
    for(i;i >= 0;i--)
    {
        if(digits[i] < 9)
        {
            digits[i]++;
            *returnSize = digitsSize;
            return digits;
        }
        digits[i] = 0; 
    }
    int *re =(int *)malloc(sizeof(int)*(digitsSize+1));
    re[0]=1;
    for(int j=1;j<(digitsSize+1);j++)
        re[j]=0;
    * returnSize=digitsSize+1;
    return re;
}
// @lc code=end