//
// @lc app=leetcode.cn id=279 lang=java
//
// [279] perfect-squares
//
class Solution {
    public int numSquares(int n) {
    //一，先判断由1个平方数组成的
    //如果n是平方数，直接返回1即可，表示n由
    //1个平方数组成
    if (is_square(n))
        return 1;
    //如果n是4的倍数，就除以4，因为4是2的平方，
    //如果n可以由m个完全平方数组成，那么4n也
    //可以由m个完全平方数组成
    while ((n & 3) == 0)
        n >>= 2;
    //二，在判断由4个平方数组成的
    //如果n是4的倍数，在上面代码的执行中就会一直除以4，
    //直到不是4的倍数为止，所以这里只需要判断n=(8b+7)
    //即可
    if ((n & 7) == 7)
        return 4;
    int sqrt_n = (int) (Math.sqrt(n));
    //三，接着判断由2个平方数组成的
    //下面判断是否能由2个平方数组成
    for (int i = 1; i <= sqrt_n; i++) {
        if (is_square(n - i * i)) {
            return 2;
        }
    }
    //四，剩下的只能由3个平方数组成了
    //如果上面都不成立，根据拉格朗日四平方和定理
    //只能由3个平方数组成了
    return 3;
}

//判断n是否是平方数
public boolean is_square(int n) {
    int sqrt_n = (int) (Math.sqrt(n));
    return sqrt_n * sqrt_n == n;
}
}
// @lc code=end