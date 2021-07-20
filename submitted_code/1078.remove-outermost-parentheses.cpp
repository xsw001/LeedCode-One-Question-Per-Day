//
// @lc app=leetcode.cn id=1078 lang=cpp
//
// [1078] remove-outermost-parentheses
//
class Solution {
public:
    string removeOuterParentheses(string S) {
        string res="";
        string res1="";
        string res2="";
        stack<char>st;
        int i=0;
        for(;i<S.size();++i){  //得到原语分解，一个原语被存在res中
            if(S[i]=='('){
                st.push(S[i]);
                res.push_back(S[i]);
            }
            if(S[i]==')'){
                st.pop();
                res.push_back(S[i]);
            }
            if(st.empty()){//说明得到一个原语，现在给它脱衣服
                for(int j=1;j<res.size()-1;++j){ //脱掉每部分最外层括号
                    res1.push_back(res[j]);
                }
                res2+=res1;
                res="";
                res1="";
            }
        }
        return res2;
    }
};
// @lc code=end