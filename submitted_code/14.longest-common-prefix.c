//
// @lc app=leetcode.cn id=14 lang=c
//
// [14] longest-common-prefix
//
char * longestCommonPrefix(char ** strs, int strsSize){
    if(strsSize == 0)
        return "";
    if(strsSize == 1)
        return strs[0];
    char *s=(char*)malloc(sizeof(char));
    int i=1,j=0;
    while(strs[i][j]!='\0'){
        while(s[j]==strs[i][j] && i<strsSize)
            i++;
        if(i==strsSize){
            j++;
            i=0;
        }
        else break;
    }
    if(j==0)
        return "";
    return s;
}
// @lc code=end