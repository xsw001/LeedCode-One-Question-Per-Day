//
// @lc app=leetcode.cn id=897 lang=java
//
// [897] prime-palindrome
//
class Solution {
    public int primePalindrome(int n) {
        if(n == 1)
            return 2;

        while(n < 200000000){
            if(isPalindrome(n) && isPrime(n))
                return n;
            ++n;
            if (10_000_000 < n && n < 100_000_000)
                n = 100_000_000;
        }
        return n;
    }
    boolean isPrime( int num ){
        //两个较小数另外处理
        if(num ==2|| num==3 )
            return true;
        //不在6的倍数两侧的一定不是质数
        if(num %6!= 1&&num %6!= 5)
            return false;
        int tmp = (int)Math.sqrt(num);
        //在6的倍数两侧的也可能不是质数
        for(int i= 5;i <=tmp; i+=6 )
            if(num %i== 0||num %(i+ 2)==0 )
                return false;
        //排除所有，剩余的是质数
        return true;
    }
    boolean isPalindrome(int num){
        String s = "" + num;
        int l = 0, r = s.length()-1;
        while(l < r)
            if(s.charAt(l++) != s.charAt(r--))
                return false;
        return true;
    }
}
// @lc code=end