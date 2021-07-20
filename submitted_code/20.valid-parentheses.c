//
// @lc app=leetcode.cn id=20 lang=c
//
// [20] valid-parentheses
//
bool isValid(char * s){

    if (s==NULL || strlen(s)<=0) return true;
    int top = 0;
    char *stack = (char*)malloc(strlen(s)+1);
    for (int i = 0; i<strlen(s); i++){
        if(s[i]=='(' || s[i]=='{' || s[i]=='['){
            stack[top++] = s[i];
        }else{
            if(--top < 0) return false;
            if(s[i]==')' && stack[top] != '(') return false;
            if(s[i]=='}' && stack[top] != '{') return false;
            if(s[i]==']' && stack[top] != '[') return false;
        }
    }
    if (top > 0) return false;
    return true;
}
// @lc code=end