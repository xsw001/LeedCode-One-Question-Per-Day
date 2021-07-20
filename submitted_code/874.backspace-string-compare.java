//
// @lc app=leetcode.cn id=874 lang=java
//
// [874] backspace-string-compare
//
class Solution {

    public boolean backspaceCompare(String S, String T) {
        System.out.println(method(S));
        System.out.println(method(T));
        return method(S).equals(method(T));
    }

    public String method(String str){
        ArrayList<Character> list = new ArrayList();
        char[] ch = str.toCharArray();
        int num = 0;
        for(int i=0; i<ch.length;++i){
            if(ch[i] != '#'){
                list.add(ch[i]);
                num++;
            }else if(!list.isEmpty()){
                list.remove(--num);
            }
        }
        return list.toString();
    }
}
// @lc code=end