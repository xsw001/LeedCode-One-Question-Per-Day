//
// @lc app=leetcode.cn id=3 lang=c
//
// [3] longest-substring-without-repeating-characters
//
int lengthOfLongestSubstring(char * s){
    int i,j,k=0,max=0,cnt=0,lens=0;
    for(int m=0;s[m]!='\0';m++)
        lens++;
    for(i=0;i<lens;i++){
        for(j=k;j<i;j++){
            if(s[i]==s[j]){
                if(cnt>max)
                max=cnt;
                cnt=i-j;
                k=j+1;
                break;
            }
        }
        if(j==i)
        cnt++;
        if(cnt>max)
        max=cnt;
    }
    return max;
}
// @lc code=end