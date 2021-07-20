//
// @lc app=leetcode.cn id=1205 lang=java
//
// [1205] defanging-an-ip-address
//
class Solution {
    public String defangIPaddr(String address) {
        StringBuilder sb =  new StringBuilder();
        for(int i = 0;i<address.length();++i){
            if(address.charAt(i)!='.'){
                sb.append(String.valueOf(address.charAt(i)));
            }else{
                sb.append("[.]");
            }
        }
        return sb.toString();
    }
}
// @lc code=end